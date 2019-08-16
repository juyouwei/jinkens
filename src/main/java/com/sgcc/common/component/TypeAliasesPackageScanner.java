package com.sgcc.common.component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import com.sgcc.utils.PropertyUtil;
@Component
public class TypeAliasesPackageScanner {
	
	 public static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
	 static String  ENUM_PACKAGE  = PropertyUtil.get("mybatis.type-enums-package");
	 static String  ENTITY_PACKAGE  = PropertyUtil.get("mybatis.type-aliases-package");
	 public final static String PACKAGE_PATTERN_FOR_ENUM =
	            ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
	            + ClassUtils.convertClassNameToResourcePath(ENUM_PACKAGE)
	            + DEFAULT_RESOURCE_PATTERN;
	
	 public final static String PACKAGE_PATTERN_FOR_ENTITY =
	            ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
	            + ClassUtils.convertClassNameToResourcePath(ENTITY_PACKAGE)
	            + DEFAULT_RESOURCE_PATTERN;
	protected final static Logger LOGGER = LoggerFactory.getLogger(TypeAliasesPackageScanner.class);
    private static ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
   /* private TypeFilter[] entityTypeFilters = new TypeFilter[]{new AnnotationTypeFilter(Entity.class, false),
            new AnnotationTypeFilter(Embeddable.class, false),
            new AnnotationTypeFilter(MappedSuperclass.class, false),
            new AnnotationTypeFilter(org.hibernate.annotations.Entity.class, false)};*/


    public String getTypeAliasesPackages() {
        Set<String> packageNames = new TreeSet<String>();
        String typeAliasesPackage ="";
        try {
            //加载所有的资源
            Resource[] enumResources = resourcePatternResolver.getResources(PACKAGE_PATTERN_FOR_ENUM);
            Resource[] entityResources = resourcePatternResolver.getResources(PACKAGE_PATTERN_FOR_ENTITY);
            List<Resource[]> resourceList=new ArrayList<>();
            resourceList.add(enumResources);
            resourceList.add(entityResources);
            MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            //遍历资源
            for (Resource[] resources : resourceList) {
            	for(Resource resource:resources){
                if (resource.isReadable()) {
                    MetadataReader reader = readerFactory.getMetadataReader(resource);
                    String className = reader.getClassMetadata().getClassName();
                    //eg:com.muses.taoshop.item.entity.ItemBrand
                    LOGGER.info("className : {} "+className);
                    try{
                        //eg:com.muses.taoshop.item.entity
                        LOGGER.info("packageName : {} "+Class.forName(className).getPackage().getName());
                        packageNames.add(Class.forName(className).getPackage().getName());
                    }catch (ClassNotFoundException e){
                        LOGGER.error("classNotFoundException : {} "+e);
                    }
                }
            }
           }
        } catch (IOException e) {
            LOGGER.error("ioException =>: {} " + e);
        }
        //集合不为空的情况，拼装一下数据
        if (!CollectionUtils.isEmpty(packageNames)) {
        	String CONFIG_LOCATION_DELIMITERS = ",";
            typeAliasesPackage = StringUtils.join(packageNames.toArray() , CONFIG_LOCATION_DELIMITERS);
        }else{
            LOGGER.info("set empty,size:{} "+packageNames.size());
        }
        return typeAliasesPackage;
    }

}
