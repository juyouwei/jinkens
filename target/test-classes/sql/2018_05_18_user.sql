CREATE TABLE `user_project_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL,
  `shengxinbao_plan_id` bigint(20) NOT NULL COMMENT '计划id',
  `join_time` datetime DEFAULT NULL COMMENT '加入时间',
  `join_money` decimal(15,2) DEFAULT NULL COMMENT '加入金额',
  `matching_money` decimal(15,2) DEFAULT NULL COMMENT '待匹配金额',
  `lock_end_time` datetime DEFAULT NULL COMMENT '锁定期结束时间',
  `source` varchar(20) DEFAULT NULL COMMENT '来源(app,web)',
  `status` varchar(20) DEFAULT NULL COMMENT '状态:锁定中(locking)可退出(allow_quit)已退出(quit)',
  `user_coupon_id` varchar(32) DEFAULT NULL COMMENT '用户优惠券ID',
  `roll_times` int(11) DEFAULT NULL COMMENT '滚动次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9372 DEFAULT CHARSET=utf8 COMMENT='用户加入计划表';




