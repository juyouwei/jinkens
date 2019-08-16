package com.sgcc.utils.mybatis;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import com.sgcc.common.CommonEnum;
public class DefaultEnumTypeHandler extends BaseTypeHandler<CommonEnum> {
	 
    private Class<CommonEnum> type;
 
    public DefaultEnumTypeHandler(){};
 
    public DefaultEnumTypeHandler(Class<CommonEnum> type) {
        if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
    }
 
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, CommonEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setObject(i, parameter.getCode());
    }
 
    @Override
    public CommonEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convert(rs.getInt(columnName));
    }
 
    @Override
    public CommonEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convert(rs.getInt(columnIndex));
    }
 
    @Override
    public CommonEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convert(cs.getInt(columnIndex));
    }
 
    private CommonEnum convert(int status){
        CommonEnum[] objs = type.getEnumConstants();
        for(CommonEnum em: objs){
        	String code = em.getCode();
            if(Integer.valueOf(code) == status){
                return  em;
            }
        }
        return null;
    }

	

	

}
