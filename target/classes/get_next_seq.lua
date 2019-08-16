-- 获取最大的序列号,样例为16081817202494579
-- 从redis中获取到的序列如果小于传入的序列号,就把redis中的序列号置为当前序列号,并返回给调用者
-- 从redis中获取到的序列如果大于传入的序列号,就按照增长规则递增,并返回给调用者
-- 通过这样的方式保证序列号的唯一性

local function get_max_seq()
--KEYS[1]:第一个参数代表存储序列号的key  相当于代码中的业务类型
local key = tostring(KEYS[1])
--KEYS[2]:第二个参数代表序列号增长速度  
local incr_amoutt = tonumber(KEYS[2])
--KEYS[3]:第三个参数为序列号 (yyMMddHHmmss + 两位随机数)
local seq = tostring(KEYS[3])
local month_in_seconds = 24 * 60 * 60 * 30
if (1 == redis.call('setnx', key,  seq)) then
redis.call('expire', key, month_in_seconds)
return seq
else
local prev_seq = redis.call('get',key)
print(prev_seq)
if (prev_seq < seq ) then
redis.call('set',key,seq)
return seq
else
redis.call('incrby', key,  incr_amoutt)
return redis.call('get', key)
end
end
end
return get_max_seq()