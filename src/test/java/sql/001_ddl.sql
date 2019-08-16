CREATE TABLE leo_product
(
  id                      BIGINT UNSIGNED           AUTO_INCREMENT PRIMARY KEY,
  name                    VARCHAR(100)     NOT NULL UNIQUE COMMENT '产品名称',
  start_invest_amount     DECIMAL(16, 4)   NOT NULL COMMENT '起投金额',
  increment_invest_amount DECIMAL(16, 4)   NOT NULL COMMENT '递增投资金额',
  warning_amount          DECIMAL(16, 4)   NOT NULL COMMENT '预警金额',
  is_regular              TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否定期产品',
  is_quitable             TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否可预约退出',
  lock_days               INT(10) UNSIGNED NULL DEFAULT NULL COMMENT '锁定天数，只针对计划类产品。',
  roll_days               VARCHAR(100) COMMENT '滚动期限(天)，使用逗号分隔。例如： 30,90， 代表可匹配30天和90天的资产。滚动类产品只能配置一种滚动期限。',
  base_interest_rate      DECIMAL(10, 6)   NOT NULL COMMENT '基准利率',
  ladder_interest_rate    VARCHAR(100)     NOT NULL COMMENT '阶梯利率，使用逗号分隔。例如： 0.01,0.02,0.03， 代表在滚动的第二次、第三次、第四次及往后，分别加息1%， 2%， 3%',
  is_invest_interest      TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '首投是否即投即生息',
  is_reinvest_interest    TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '复投是否即投即生息',
  reinvest_type           TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '复投类型, 0:不复投, 1:本金复投, 2:本息复投',
  status                  TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '产品状态, 1:正常, 0:下线, 2:暂停',
  create_time             DATETIME                  DEFAULT CURRENT_TIMESTAMP,
  update_time             DATETIME                  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
  ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci COMMENT '产品表';


CREATE TABLE leo_order
(
  id              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  user_id         VARCHAR(32) NOT NULL COMMENT '用户ID',
  product_id      BIGINT UNSIGNED NOT NULL COMMENT '产品ID',
  product_rate    DECIMAL(10,6) NOT NULL COMMENT '加入时的产品利率',
  total_money     DECIMAL(16, 4) NOT NULL COMMENT '投资金额',
  investing_money     DECIMAL(16, 4) NOT NULL COMMENT '在投金额',
  remain_money    DECIMAL(16, 4) NOT NULL COMMENT '剩余金额',
  matching_money  DECIMAL(16,4) NOT NULL COMMENT '待匹配金额',
  roll_times      INT(11) UNSIGNED DEFAULT NULL COMMENT '滚动轮数(初始值为1)',
  lock_end_time   DATETIME NULL DEFAULT NULL COMMENT '锁定期结束时间',
  status          TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '订单状态, 0:待匹配, 10:部分匹配, 20:匹配完成, 30:全部投资成功, 40:全部放款成功, 50:完成',
  user_coupon_id  VARCHAR(50) NULL DEFAULT NULL COMMENT '用户优惠券id',
  source          VARCHAR(10) NOT NULL COMMENT '来源(web/app/h5)',
  create_time     DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_time     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci COMMENT '用户订单表';

CREATE TABLE `leo_invest_authorization` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` VARCHAR(32) NOT NULL COMMENT '用户id',
    `order_id` VARCHAR(100) NOT NULL COMMENT '订单号，即信自动投标签约(页面)接口返回，在自动投标申请时使用',
    `single_invest_limit` DECIMAL(16,4) NOT NULL COMMENT '单笔投资金额上限',
    `total_invest_limit` DECIMAL(16,4) NOT NULL COMMENT '自动投标总金额上限',
    `status`    TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态, 0:待签约;1:已签约;2:签约失败;3:签约过期',
    `query_fail_times` INT(5) NULL DEFAULT NULL COMMENT '主动查询失败次数',
    `deadline` DATETIME NULL DEFAULT NULL COMMENT '签约有效期',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
COMMENT='用户自动投标授权表' COLLATE='utf8_general_ci' ENGINE=InnoDB;


CREATE TABLE leo_loan_pool_item (
    id                    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_id            BIGINT UNSIGNED  NOT NULL COMMENT '产品id',
    loan_id               VARCHAR(50) NOT NULL COMMENT '标的id',
    loan_money            DECIMAL(16, 4) NOT NULL COMMENT '标的筹款金额',
    match_remain_amount   DECIMAL(16, 4) NOT NULL COMMENT '匹配剩余金额',
    matching_success_time DATETIME NULL DEFAULT NULL COMMENT '全部匹配成功时间',
    status                TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '匹配状态, 0:待匹配, 1:部分匹配, 2:匹配完成',
    create_time           DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time           DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci COMMENT '标的池表';

CREATE TABLE leo_invest (
    id        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id  BIGINT UNSIGNED  NOT NULL COMMENT '订单id',
    user_id   VARCHAR(32) NOT NULL COMMENT '用户ID',
    loan_id   VARCHAR(50) NOT NULL COMMENT '标的id',
    auth_code VARCHAR(50) NULL DEFAULT NULL COMMENT '托管方投标授权码',
    jixin_order_id VARCHAR(50) NULL DEFAULT NULL COMMENT '请求即信的订单号',
    money     DECIMAL(16, 4) NOT NULL COMMENT '投资金额',
    status    TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '投资状态, 0:待投资, 1:投资成功, 2:投资失败,3:还款中,4:已完成',
    roll_times INT(10) UNSIGNED NOT NULL DEFAULT '1' COMMENT '第几轮投资',
    create_time     DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci COMMENT '用户投资表';


CREATE TABLE leo_product_extra_interest_rate_config (
  id                BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  product_id        BIGINT UNSIGNED NOT NULL COMMENT '产品id',
  interest_rate     DECIMAL(10,6) NULL DEFAULT NULL COMMENT '加息利率',
  period            INT(11) UNSIGNED DEFAULT NULL COMMENT '加息期限(单位:天)',
  start_time        DATETIME NOT NULL COMMENT '开始时间',
  end_time          DATETIME NOT NULL COMMENT '结束时间',
  channel_backlist  VARCHAR(500) NULL DEFAULT NULL COMMENT '渠道黑名单, 逗号分隔？？',
  create_time       DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_time       DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci COMMENT '加息配置表';

CREATE TABLE leo_product_sell_summary
(
  id                    BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  product_id            BIGINT UNSIGNED NOT NULL COMMENT '产品ID',
  total_sell_amount     DECIMAL(16, 4) NOT NULL COMMENT '总计购买金额',
  actual_invest_amount  DECIMAL(16, 4) NOT NULL COMMENT '实际在投金额',
  total_raise_amount    DECIMAL(16, 4) NOT NULL COMMENT '当前筹款总金额',
  open_amount           DECIMAL(16, 4) NOT NULL COMMENT '开放金额，管理员后台设置',
  current_sell_amount   DECIMAL(16, 4) NOT NULL COMMENT '当前可购买金额，管理员修改开放金额后与开放金额相同',
  total_sell_count      INT NOT NULL COMMENT '总计购买次数',
  create_time           DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_time           DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci COMMENT '产品销售汇总表';

CREATE TABLE leo_product_extend (
  id                      BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  product_id              BIGINT UNSIGNED NOT NULL COMMENT '产品id',
  econtract_id            VARCHAR(100) COMMENT '电子合同id',
  short_desc              VARCHAR(100) NULL DEFAULT NULL COMMENT '产品短描述',
  is_extra_interest_rate  TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否加息',
  is_allow_coupon         TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否可使用优惠券',
  is_only_rookie          TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否只允许新手',
  is_web_show             TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否在web展示',
  is_app_show             TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否在app展示',
  is_h5_show              TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否在H5展示',
  create_time             DATETIME DEFAULT CURRENT_TIMESTAMP,
  update_time             DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci COMMENT '产品属性扩展表';

CREATE TABLE leo_platform_award (
    id  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id  BIGINT UNSIGNED  NOT NULL COMMENT '用户订单id',
    invest_id BIGINT(20) NULL DEFAULT NULL COMMENT '投资id',
    money   DECIMAL(16, 4) NOT NULL COMMENT '平台奖励金额',
    interest_rate DECIMAL(10,6) NOT NULL COMMENT '加息利率',
    roll_times  INT UNSIGNED NULL COMMENT '滚动次数',
    status    TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态, 0:待转账;1:转账中;2:转账成功;3:转账失败',
    type  TINYINT NOT NULL COMMENT '类型(0:现金券奖励;1:加息券奖励;2:礼金券奖励;3:平台加息奖励)',
    stop_interest_time   DATETIME NULL DEFAULT NULL COMMENT '停止计息时间',
    transfer_success_time  DATETIME NULL DEFAULT NULL COMMENT '转账成功时间',
    create_time     DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time     DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci COMMENT '平台奖励表';

CREATE TABLE `leo_appointment_exit_order` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `order_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '订单id,关联leo_order表',
    `user_id` VARCHAR(32) NOT NULL COMMENT '用户id',
    `money` DECIMAL(16,4) NOT NULL DEFAULT '0.0000' COMMENT '申请退出金额',
    `exited_money` DECIMAL(16,4) NULL DEFAULT '0.0000' COMMENT '已退出金额',
    `appointment_exit_time` DATETIME NOT NULL COMMENT '预计退出时间',
    `status`    TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态, 0:退出中;1:部分退出;2:完成;3:撤销',
    `complete_time` DATETIME NULL DEFAULT NULL COMMENT '完成时间',
    `roll_times` INT(11) NOT NULL COMMENT '滚动轮数',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
COMMENT='用户预约退出订单' COLLATE='utf8_general_ci' ENGINE=InnoDB;
CREATE TABLE `leo_appointment_exit_order_bill` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` VARCHAR(32) NOT NULL COMMENT '用户id',
    `appointment_exit_order_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '用户预约退出订单id',
    `invest_repay_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '投资还款id',
    `exit_money` DECIMAL(16,4) NOT NULL COMMENT '退出金额',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
COMMENT='用户预约退出订单流水' COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `leo_loan_repay` (
	`id` BIGINT(100) NOT NULL,
	`loan_id` VARCHAR(50) NOT NULL COMMENT '标的id',
	`corpus` DECIMAL(16,4) NULL DEFAULT '0.0000' COMMENT '还款本金',
	`default_interest` DECIMAL(16,4) NULL DEFAULT '0.0000' COMMENT '罚息',
	`interest` DECIMAL(16,4) NOT NULL COMMENT '利息',
	`fee` DECIMAL(16,4) NULL DEFAULT '0.0000' COMMENT '手续费',
	`length` INT(11) NULL DEFAULT NULL,
	`period` INT(11) NOT NULL COMMENT '当前期数',
	`status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '还款状态(0:还款中,1:逾期,2:坏账,3:还款已申请,4:还款完成)',
	`repay_day` DATETIME NOT NULL COMMENT '预计还款时间',
	`repay_success_time` DATETIME NULL DEFAULT NULL COMMENT '还款成功时间',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `loan_id` (`loan_id`)
)
COMMENT='借款人还款计划表' COLLATE='utf8_general_ci' ENGINE=InnoDB;


CREATE TABLE `leo_invest_repay` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`invest_id` BIGINT(20) NOT NULL COMMENT '投资id',
	`loan_id` VARCHAR(100) NOT NULL COMMENT '投资标的的id',
	`corpus` DECIMAL(16,4) NOT NULL DEFAULT '0.0000' COMMENT '还款本金',
	`default_interest` DECIMAL(16,4) NULL DEFAULT '0.0000' COMMENT '罚息',
	`interest` DECIMAL(16,4) NOT NULL COMMENT '还款利息',
	`fee` DECIMAL(16,4) NOT NULL DEFAULT '0.0000' COMMENT '还款手续费',
	`loan_user_fee` DECIMAL(16,4) NULL DEFAULT '0.0000',
	`length` INT(11) NULL DEFAULT NULL COMMENT '本期长度',
	`period` INT(11) NOT NULL COMMENT '当前还款为第几期',
	`status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '还款状态(0:还款中,1:逾期,2:坏账,3:还款已申请,4:还款完成)',
	`repay_day` DATETIME NOT NULL COMMENT '预计还款时间',
	`repay_success_time` DATETIME NULL DEFAULT NULL COMMENT '还款成功时间',
	`create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `invest_id` (`invest_id`)
)
COMMENT='投资人还款计划表' COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `leo_matching_record` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`order_id` BIGINT NOT NULL COMMENT '订单id',
	`round` INT NOT NULL COMMENT '匹配轮数',
	`matching_money` DECIMAL(16,4) NOT NULL COMMENT '本轮匹配金额',
	`matching_end_time` DATETIME NULL COMMENT '匹配结束时间',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `order_id` (`order_id`)
)
COMMENT='匹配记录表' COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `leo_repay_reinvest` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`user_id` VARCHAR(32) NOT NULL,
	`order_id` BIGINT(20) UNSIGNED NOT NULL,
	`invest_repay_id` BIGINT(20) UNSIGNED NOT NULL,
	`money` DECIMAL(16,4) NOT NULL COMMENT '复投金额',
	`type` TINYINT(4) NOT NULL COMMENT '类型',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
)
COMMENT='还款复投表' COLLATE='utf8_general_ci' ENGINE=InnoDB;
CREATE TABLE `leo_auto_repay_plan` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`loan_repay_id` BIGINT(100) NOT NULL COMMENT '借款人还款计划id',
	`repay_user_id` VARCHAR(50) NOT NULL COMMENT '还款用户id',
	`repay_money` DECIMAL(16,4) NULL DEFAULT NULL COMMENT '还款金额(本金+利息+手续费+罚息)',
	`status` TINYINT(4) NOT NULL COMMENT '自动还款状态(0:初始化,10:待还款,20:待审核,30:逾期,40:完成)',
  `repay_type` TINYINT(4) NOT NULL COMMENT '还款类型(0:提前还款,10:正常还款,20:逾期还款)',
	`capital_status` TINYINT(4) NOT NULL COMMENT '资金状态(0:待冻结,10:已冻结,20:余额不足,30:已完成)',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `loan_repay_id` (`loan_repay_id`),
	INDEX `repay_user_id` (`repay_user_id`)
)
COMMENT='自动还款计划表' COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `leo_repay_authorization` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`user_id` VARCHAR(50) NOT NULL COMMENT '用户id',
	`max_repay_money` DECIMAL(16,4) NOT NULL COMMENT '最大还款金额',
	`status` TINYINT(4) NOT NULL COMMENT '还款授权签约状态（0:待签约,10:已签约,20:签约成功,30:签约过期）',
	`deadline` DATETIME NOT NULL COMMENT '签约到期日期',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `user_id` (`user_id`)
)
COMMENT='还款授权表' COLLATE='utf8_general_ci' ENGINE=InnoDB;

CREATE TABLE `leo_platform_award_bill` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`platform_award_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '平台奖励表id',
	`invest_id` BIGINT(20) UNSIGNED NOT NULL COMMENT '投资记录id',
	`money` DECIMAL(16,4) NOT NULL COMMENT '平台奖励日收益',
	`create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
)
COMMENT='平台奖励流水表' COLLATE='utf8_general_ci' ENGINE=InnoDB;
CREATE TABLE `leo_retry` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`source_id` VARCHAR(50) NOT NULL COMMENT '业务id',
	`retry_way` TINYINT(4) UNSIGNED NULL DEFAULT NULL COMMENT '重试方式(0:手动;1:自动)',
	`status` TINYINT(4) UNSIGNED NOT NULL COMMENT '状态(0:待重试,1:重试成功,2:重试失败,3:处理中)',
	`retry_times` INT(10) UNSIGNED NULL DEFAULT '0' COMMENT '重试次数',
	`type` TINYINT(4) UNSIGNED NOT NULL COMMENT '类型(0:复投)',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
)
ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_general_ci COMMENT '重试处理表';


CREATE TABLE `leo_order_esign_record` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`order_id` BIGINT NOT NULL COMMENT '订单id',
	`doc_id` INT NULL COMMENT '保全文档id',
	`status` TINYINT(4) NOT NULL COMMENT '签章状态（0：初始化，1：合同已生成，2：生成合同异常，3：签署中，4：签章异常，5：签章成功）',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `order_id` (`order_id`)
)
COMMENT='用户订单签章记录表' COLLATE='utf8_general_ci' ENGINE=InnoDB;
CREATE TABLE `leo_apply_appointment_exit_bill` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`appointment_exit_order_id` BIGINT(20) NOT NULL COMMENT '预约退出订单id',
	`money` DECIMAL(16,4) NOT NULL COMMENT '申请预约退出金额',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `appointment_exit_order_id` (`appointment_exit_order_id`)
)
COMMENT='申请预约退出流水' COLLATE='utf8_general_ci' ENGINE=InnoDB;


CREATE TABLE `leo_invest_esign_record` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`invest_id` BIGINT NOT NULL,
	`doc_id` INT NULL COMMENT '文档id',
	`status` TINYINT(4) NOT NULL COMMENT '状态0：初始化，1：合同已生成，2：签署中，3：签署异常，4：签署完成',
	`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `invest_id` (`invest_id`)
)
COMMENT='投资签章记录表' COLLATE='utf8_general_ci' ENGINE=InnoDB;

ALTER TABLE `leo_invest`
	ADD COLUMN `lend_order_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '放款订单号' AFTER `jixin_order_id`;
ALTER TABLE `leo_invest_repay`
	ADD COLUMN `repay_order_id` VARCHAR(50) NULL COMMENT '还款时给即信的订单号' AFTER `period`;
ALTER TABLE `leo_loan_repay`
	ADD COLUMN `repay_order_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '还款订单号' AFTER `repay_success_time`;
ALTER TABLE `leo_platform_award`
	ADD COLUMN `voucher_pay_order_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '红包转账订单号' AFTER `transfer_success_time`;

CREATE TABLE `leo_user_bank_card` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`user_id` VARCHAR(32) NOT NULL COMMENT '用户ID',
	`card_no` VARCHAR(30) NOT NULL COMMENT '银行卡号',
	`bank_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '银行名称',
	`opening_bank_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '开户银行名称',
	`bank_code` VARCHAR(20) NULL DEFAULT NULL COMMENT '银行编号',
	`card_bank_cnaps` VARCHAR(20) NULL DEFAULT NULL COMMENT '绑定银行联行号',
	`status` TINYINT(4) NOT NULL COMMENT '绑卡状态，0:处理中；1：已绑定；2：已解绑',
	`create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
	`update_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	INDEX `idx_user_id_status` (`user_id`, `status`)
)
COMMENT='用户绑定银行卡表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=3084
;
