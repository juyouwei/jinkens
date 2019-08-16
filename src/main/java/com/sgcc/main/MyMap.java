package com.sgcc.main;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;



public class MyMap {
	public static void main(String[] args) {
		
		    String paramStr="江苏省镇江市潜山县源潭镇会朱新屋组33号";
			String newAddress = "";
			String uuid = UUID.randomUUID().toString();
			if (!StringUtils.isBlank(paramStr)) {
				int position = 0;
				if (!paramStr.contains("区") && !paramStr.contains("县")) {
					newAddress = address(paramStr, uuid);
				} else {
					if (paramStr.contains("区")) {
						position = paramStr.indexOf("区");
					} else {
						position = paramStr.indexOf("县");
					}

					String firstName = "";
					String lastName = "";
					firstName = StringUtils.left(paramStr, position + 1);
					lastName = StringUtils.right(paramStr, 5);
					char[] characters = lastName.toCharArray();

					for (int i = 3; i >= 0; --i) {
						if (characters[i] >= 19968 && characters[i] <= '龻') {
							lastName = StringUtils.right(lastName, 4 - i);
							break;
						}
					}

					newAddress = firstName.concat("******").concat(lastName);
					System.out.println(newAddress);
				}
			}

			
		}
        
	
private static String address(String paramStr, String uuid)
{
  String newAddress = "";
  if (!StringUtils.isBlank(paramStr)) {
    if ((paramStr.length() < 6) && (paramStr.length() > 3))
    {
      newAddress = StringUtils.left(paramStr, 1)
        .concat(StringUtils.leftPad(
        StringUtils.right(StringUtils.right(paramStr, paramStr
        .length() - 1), 2), paramStr
        .length() - 1, "*"));
    } else if ((paramStr.length() < 10) && (paramStr.length() > 5))
      newAddress = StringUtils.leftPad(StringUtils.right(paramStr, 5), paramStr.length(), "*");
    else if (paramStr.length() > 9)
    {
      newAddress = StringUtils.left(paramStr, paramStr.length() - 9)
        .concat(StringUtils.leftPad(StringUtils.right(StringUtils.right(paramStr, 9), 
        5), 
        StringUtils.right(paramStr, 9)
        .length(), "*"));
    }
    else newAddress = paramStr;

  }
  return newAddress;
}
}
