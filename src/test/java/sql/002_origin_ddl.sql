CREATE TABLE `account` (
  `id`                               BIGINT(20)    NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id`                          VARCHAR(50)   NOT NULL COMMENT '用户id',
  `total_demand_invest`              DOUBLE        NOT NULL DEFAULT '0' COMMENT '活期项目累计投资金额,即使赎回也不扣除',
  `frozen_demand_money`              DOUBLE        NOT NULL DEFAULT '0' COMMENT '活期项目冻结金额',
  `total_demand_interest`            DOUBLE        NOT NULL DEFAULT '0' COMMENT '活期项目累计收益',
  `can_redeem_interest`              DOUBLE        NOT NULL DEFAULT '0' COMMENT '可赎回收益',
  `already_redeemed_interest`        DOUBLE        NOT NULL DEFAULT '0' COMMENT '已赎回收益',
  `can_redeem_corpus`                DOUBLE        NOT NULL DEFAULT '0' COMMENT '可赎回本金',
  `already_redeemed_corpus`          DOUBLE        NOT NULL DEFAULT '0' COMMENT '已赎回本金',
  `total_demand_lock_corpus`         DOUBLE        NOT NULL DEFAULT '0' COMMENT '活期项目锁定期本金',
  `grant_money`                      DOUBLE(10, 2) NOT NULL DEFAULT '0.00' COMMENT '用户赠款余额，（比如现金券余额）',
  `frozen_grant_money`               DOUBLE(10, 2) NOT NULL DEFAULT '0.00' COMMENT '用户赠款冻结的余额',
  `jiangxi_bank_normal_balance`      DOUBLE(10, 2) NOT NULL DEFAULT '0.00' COMMENT '江西银行普通账户可用余额',
  `jiangxi_bank_red_envlope_balance` DOUBLE(10, 2) NOT NULL DEFAULT '0.00' COMMENT '江西银行红包账户可用余额',
  `jiangxi_bank_fee_balance`         DOUBLE(10, 2) NOT NULL DEFAULT '0.00' COMMENT '江西银行手续费账户可用余额',
  `jiangxi_bank_guarantee_balance`   DOUBLE(10, 2) NOT NULL DEFAULT '0.00' COMMENT '江西银行担保账户可用余额',
  `jiangxi_bank_normal_frozen`       DOUBLE(10, 2) NOT NULL DEFAULT '0.00' COMMENT '江西银行普通账户冻结金额',
  `yeepay_balance`                   DOUBLE(10, 2) NOT NULL DEFAULT '0.00' COMMENT '易宝支付账户可用余额',
  `yeepay_frozen`                    DOUBLE(10, 2) NOT NULL DEFAULT '0.00' COMMENT '易宝支付账户冻结金额',
  `lock_money`                       DOUBLE(10, 2) NOT NULL DEFAULT '0.00' COMMENT '锁定金额',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE='utf8_general_ci' COMMENT ='用户账户表';

CREATE TABLE `label_type` (
  `id`                  BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
  `label_type_id`       VARCHAR(32) NOT NULL COMMENT '标签ID',
  `label_type_describe` VARCHAR(50) NOT NULL COMMENT '标签描述',
  PRIMARY KEY (`id`),
  INDEX `id` (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE='utf8_general_ci' COMMENT ='项目标签表';


CREATE TABLE `project` (
  `primaryId`                      BIGINT(20)          NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `description`                    LONGTEXT            NULL,
  `name`                           VARCHAR(100)        NOT NULL,
  `lable_type_id`                  BIGINT(20)          NULL     DEFAULT NULL COMMENT '标签id',
  `feature`                        VARCHAR(100)        NULL     DEFAULT NULL COMMENT '一句话描述的特征值',
  `rate`                           DOUBLE(10, 5)       NOT NULL DEFAULT '0.00000' COMMENT '年利率',
  `extend_rate`                    DOUBLE(10, 5)       NOT NULL DEFAULT '0.00000' COMMENT '浮动年利率',
  `number`                         VARCHAR(50)         NULL     DEFAULT NULL COMMENT '编号',
  `user_max_invest_money`          DOUBLE              NULL     DEFAULT NULL COMMENT '用户最大可投资金额',
  `total_money`                    DOUBLE              NOT NULL COMMENT '项目总金额',
  `commit_time`                    DATETIME            NOT NULL COMMENT '创建时间',
  `status`                         VARCHAR(50)         NOT NULL COMMENT '状态',
  `recommend`                      TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否是推荐。1表示是推荐，0表示不是推荐',
  `seq_num_for_recommend`          BIGINT(20)          NOT NULL DEFAULT '0' COMMENT '推荐列表的排序字段。越小越排在前面',
  `custom_picture`                 TEXT                NULL COMMENT '项目图片',
  `fakeId`                         VARCHAR(50)         NULL     DEFAULT NULL COMMENT '伪id',
  `is_allow_batch_lendpay_reissue` TINYINT(1)          NOT NULL DEFAULT '0' COMMENT '是否允许批次放款补发。1允许 0不允许',
  `is_allow_batch_bid_cancel`      TINYINT(1)          NOT NULL DEFAULT '0' COMMENT '是否允许批次撤销投资。1 允许 0 不允许',
  `sticky_post`                    TINYINT(1)          NOT NULL DEFAULT '0' COMMENT '是否置顶。1表示置顶，0表示不置顶',
  `sticky_position`                INT(20)             NOT NULL DEFAULT '0' COMMENT '置顶位置号，越小越靠前',
  PRIMARY KEY (`primaryId`),
  UNIQUE INDEX `fakeId_index` (`fakeId`),
  INDEX `FK_project_label_type` (`lable_type_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8  COLLATE='utf8_general_ci' COMMENT ='项目表';

CREATE TABLE `business_product` (
	`id` VARCHAR(32) NOT NULL,
	`product_name` VARCHAR(100) NOT NULL COMMENT '产品名称',
	`enabled` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否启用。0：不启用；1：启用',
	`expected_earnings` VARCHAR(1000) NULL DEFAULT NULL,
	`introduction` VARCHAR(255) NULL DEFAULT NULL,
	`invest_object` VARCHAR(255) NULL DEFAULT NULL,
	`invest_request` VARCHAR(255) NULL DEFAULT NULL,
	`product_timeline` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE='utf8_general_ci' COMMENT ='业务产品表，自定义的业务产品';

CREATE TABLE `reveal_mess_relation` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`type` VARCHAR(10) NOT NULL COMMENT '披露信息类型：person：个人，company:企业',
	PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE='utf8_general_ci' COMMENT ='披露信息';

CREATE TABLE `user` (
	`id` VARCHAR(32) NOT NULL,
	`username` VARCHAR(50) NOT NULL,
	`email` VARCHAR(100) NULL DEFAULT NULL,
	`init_password` VARCHAR(100) NULL DEFAULT NULL,
	`password` VARCHAR(100) NOT NULL,
	`status` CHAR(1) NOT NULL,
	`realname` VARCHAR(100) NULL DEFAULT NULL,
	`sex` VARCHAR(2) NULL DEFAULT NULL,
	`birthday` DATETIME NULL DEFAULT NULL,
	`home_address` VARCHAR(100) NULL DEFAULT NULL,
	`QQ` VARCHAR(12) NULL DEFAULT NULL,
	`MSN` VARCHAR(100) NULL DEFAULT NULL,
	`mobile_number` VARCHAR(100) NULL DEFAULT NULL,
	`isp` VARCHAR(50) NULL DEFAULT NULL COMMENT '手机号所属运营商',
	`province` VARCHAR(50) NULL DEFAULT NULL COMMENT '所在省份',
	`cityname` VARCHAR(50) NULL DEFAULT NULL COMMENT '所在城市',
	`id_card` VARCHAR(100) NULL DEFAULT NULL,
	`current_address` VARCHAR(100) NULL DEFAULT NULL,
	`nickname` VARCHAR(50) NULL DEFAULT NULL,
	`security_question1` VARCHAR(100) NULL DEFAULT NULL,
	`security_question2` VARCHAR(100) NULL DEFAULT NULL,
	`security_answer1` VARCHAR(100) NULL DEFAULT NULL,
	`security_answer2` VARCHAR(100) NULL DEFAULT NULL,
	`last_login_time` DATETIME NULL DEFAULT NULL,
	`register_time` DATETIME NOT NULL,
	`area` VARCHAR(32) NULL DEFAULT NULL,
	`comment` TEXT NULL,
	`cash_password` VARCHAR(50) NULL DEFAULT NULL,
	`area_city` VARCHAR(32) NULL DEFAULT NULL,
	`photo` VARCHAR(500) NULL DEFAULT NULL,
	`score` INT(20) NULL DEFAULT '0',
	`disable_time` DATETIME NULL DEFAULT NULL,
	`borrower_info_id` VARCHAR(32) NULL DEFAULT NULL,
	`level` VARCHAR(32) NULL DEFAULT NULL,
	`bind_ip` VARCHAR(1000) NULL DEFAULT NULL,
	`referrer` VARCHAR(1000) NULL DEFAULT NULL,
	`gas_card` VARCHAR(255) NULL DEFAULT NULL,
	`gas_station` VARCHAR(255) NULL DEFAULT NULL,
	`gas_time` DATETIME NULL DEFAULT NULL,
	`plate_number` VARCHAR(255) NULL DEFAULT NULL,
	`platform_id` VARCHAR(100) NULL DEFAULT NULL,
	`third_party_name` VARCHAR(100) NULL DEFAULT NULL COMMENT '第三方平台名称',
	`platform_user_id` VARCHAR(100) NULL DEFAULT NULL,
	`user_type` VARCHAR(15) NULL DEFAULT NULL,
	`userext_id` VARCHAR(255) NULL DEFAULT NULL,
	`platform_info` VARCHAR(5000) NULL DEFAULT NULL,
	`device_id` VARCHAR(200) NULL DEFAULT NULL,
	`client_version` VARCHAR(50) NULL DEFAULT NULL,
	`current_platform_id` VARCHAR(100) NULL DEFAULT NULL COMMENT '用户当前登录客户端渠道',
	`parent_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '不为空则为父账户  否则为子账号',
	`source` VARCHAR(50) NULL DEFAULT NULL COMMENT '用户行为来源',
	`is_new_user_for_market` TINYINT(4) NULL DEFAULT NULL,
	`idfa` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_user_area` (`area`),
	INDEX `FK36EBCB2CE80BD3` (`area_city`),
	INDEX `FK36EBCB7FED416A` (`borrower_info_id`),
	INDEX `FK36EBCBED4DABFD` (`level`),
	INDEX `FK36EBCB979E4FE9` (`userext_id`),
	INDEX `key_platform_id` (`platform_id`),
	INDEX `platform_register` (`platform_id`, `register_time`),
	INDEX `register_time_key` (`register_time`),
	INDEX `mobile_number_key` (`mobile_number`),
	INDEX `IDX_USERNAME` (`username`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8  COLLATE='utf8_general_ci' COMMENT ='用户表';


CREATE TABLE `loan_approval` (
	`loan_id` VARCHAR(32) NOT NULL,
	`reveal_id` INT(11) NULL DEFAULT NULL COMMENT '披露信息id',
	`censoring_check_message` LONGTEXT NULL,
	`censoring_recheck_mess` VARCHAR(300) NULL DEFAULT NULL,
	`commit_time` DATETIME NULL DEFAULT NULL,
	`lawyer_recheck_message` LONGTEXT NULL,
	`loan_interest` DOUBLE NOT NULL,
	`loan_money` DOUBLE NOT NULL,
	`period` INT(11) NULL DEFAULT NULL,
	`status` VARCHAR(30) NULL DEFAULT NULL,
	`subject_interest` DOUBLE NOT NULL,
	`update_time` DATETIME NULL DEFAULT NULL,
	`wind_recheck_message` LONGTEXT NULL,
	`face_message` LONGTEXT NULL COMMENT '业务面签意见',
	`approval_type` VARCHAR(20) NULL DEFAULT NULL COMMENT '借款类型：企业借款:company，个人借款：person',
	`commit_user_id` VARCHAR(32) NOT NULL,
	`lastest_update_user` VARCHAR(32) NOT NULL,
	`loan_type` VARCHAR(32) NOT NULL,
	`user_id` VARCHAR(32) NOT NULL,
	`guarantee_type` VARCHAR(100) NULL DEFAULT NULL,
	`risk_level` VARCHAR(15) NULL DEFAULT NULL COMMENT '风险评级',
	`risk_level_describ` VARCHAR(1000) NULL DEFAULT NULL COMMENT '风险评级描述',
	`credit_mess` VARCHAR(80) NULL DEFAULT NULL COMMENT '征信认证：credit,身份认证:identity,财务认证:finance,收入认证：income,实地认证：field,房产认证：house,车辆认证：car,设备认证:equipment,工作认证:work',
	`fee_rates` DOUBLE(10,5) NULL DEFAULT NULL COMMENT '手续费利率',
	`fee_pay_type` VARCHAR(20) NULL DEFAULT NULL COMMENT '手续费收取方式:先行收取，随还款时收取',
	`real_loan_user` VARCHAR(500) NULL DEFAULT NULL,
	`operation_check_message` VARCHAR(2500) NULL DEFAULT NULL COMMENT '运营发标意见',
	`lawyer_face_check_mess` VARCHAR(2500) NULL DEFAULT NULL COMMENT '法务审核面签意见',
	`lawyer_check_mortgage` VARCHAR(50) NULL DEFAULT NULL COMMENT '法务审核抵押信息，多个抵押使用，分隔',
	`lawyer_check_mortgage_mess` VARCHAR(2500) NULL DEFAULT NULL COMMENT '法务审核抵押资料意见',
	`accountant_mess` VARCHAR(1000) NULL DEFAULT NULL COMMENT '财务放款意见',
	`archive_suggestion` VARCHAR(1000) NULL DEFAULT NULL COMMENT '贷后意见',
	`final_judge_sussestion` VARCHAR(2500) NULL DEFAULT NULL COMMENT '终审意见',
	`clerk_catch_data_mess` VARCHAR(1000) NULL DEFAULT NULL COMMENT '业务补交资料说明',
	`wind_is_pass` INT(11) NULL DEFAULT NULL COMMENT '风控审核是否通过',
	`lawyer_is_pass` INT(11) NULL DEFAULT NULL COMMENT '法务审核是否通过',
	`agency_fee` DOUBLE NULL DEFAULT NULL COMMENT '中介费',
	`caution_money` DOUBLE NULL DEFAULT NULL COMMENT '保证金',
	`type` VARCHAR(50) NULL DEFAULT NULL COMMENT '审核借款类型(个人、企业2选1)',
	`partner` VARCHAR(255) NULL DEFAULT NULL COMMENT '合作机构',
	`secondary_guarantee_type` VARCHAR(100) NULL DEFAULT NULL COMMENT '次要担保方式',
	`loan_introduction` LONGTEXT NULL COMMENT '项目简介',
	`loan_purpose` VARCHAR(500) NULL DEFAULT NULL COMMENT '借款用途',
	`organization_id` BIGINT(32) NULL DEFAULT NULL COMMENT '组织机构Id',
	PRIMARY KEY (`loan_id`),
	UNIQUE INDEX `loan_id` (`loan_id`),
	INDEX `FKA38E60B2877CEC3B` (`commit_user_id`),
	INDEX `FKA38E60B22A334343` (`user_id`),
	INDEX `FKA38E60B29AD7D426` (`lastest_update_user`),
	INDEX `FK_loan_approval_reveal_mess_relation` (`reveal_id`),
	INDEX `FK_t0uv7sh1vod5brp8awclqnwfk` (`loan_type`),
	INDEX `organization_id` (`organization_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE='utf8_general_ci' COMMENT ='借款审核表';


CREATE TABLE `loan` (
	`id` VARCHAR(32) NOT NULL,
	`primaryId` BIGINT(20) NOT NULL COMMENT '自增主键',
	`loan_extra_id` BIGINT(20) NULL DEFAULT NULL,
	`actual_rate` DOUBLE NULL DEFAULT NULL,
	`business_type` VARCHAR(20) NULL DEFAULT NULL,
	`cancel_time` DATETIME NULL DEFAULT NULL,
	`cardinal_number` DOUBLE NULL DEFAULT NULL,
	`company_description` LONGTEXT NULL,
	`company_name` TEXT NULL,
	`companyno` VARCHAR(255) NULL DEFAULT NULL,
	`complete_time` DATETIME NULL DEFAULT NULL,
	`raise_completed_time` DATETIME NULL DEFAULT NULL COMMENT '筹款完成时间',
	`contract_type` TEXT NULL,
	`yeepay_interface_version` VARCHAR(10) NULL DEFAULT '1.0',
	`deadline` INT(11) NULL DEFAULT NULL,
	`deposit` DOUBLE NULL DEFAULT NULL,
	`expect_time` DATETIME NULL DEFAULT NULL,
	`fee_on_repay` DOUBLE NULL DEFAULT NULL,
	`fund_description` LONGTEXT NULL,
	`give_money_time` DATETIME NULL DEFAULT NULL,
	`raiseEndDate` DATETIME NULL DEFAULT NULL COMMENT '募集结束日期',
	`guarantee_company_description` LONGTEXT NULL,
	`guarantee_company_name` TEXT NULL,
	`guarantee_info_description` LONGTEXT NULL,
	`has_pawn` VARCHAR(10) NULL DEFAULT NULL,
	`interest_begin_time` DATETIME NULL DEFAULT NULL,
	`investor_fee_rate` DOUBLE NULL DEFAULT NULL,
	`loan_gurantee_fee` DOUBLE NULL DEFAULT NULL,
	`loan_instruction` LONGTEXT NULL,
	`loan_money` DOUBLE NULL DEFAULT NULL,
	`loan_purpose` VARCHAR(500) NULL DEFAULT NULL,
	`location` TEXT NULL,
	`min_invest_money` DOUBLE NULL DEFAULT NULL,
	`money` DOUBLE NULL DEFAULT NULL,
	`overdue_info` VARCHAR(100) NULL DEFAULT NULL,
	`pawn` VARCHAR(200) NULL DEFAULT NULL,
	`pawn_name` VARCHAR(200) NULL DEFAULT NULL,
	`policy_description` LONGTEXT NULL,
	`repay_day` DATETIME NULL DEFAULT NULL,
	`repay_period` VARCHAR(50) NULL DEFAULT NULL,
	`repay_type` VARCHAR(100) NULL DEFAULT NULL,
	`risk_description` LONGTEXT NULL,
	`risk_instruction` LONGTEXT NULL,
	`risk_level` VARCHAR(50) NULL DEFAULT NULL,
	`risk_level_describ` VARCHAR(1000) NULL DEFAULT NULL COMMENT '风险评级描述',
	`seq_num` INT(11) NULL DEFAULT NULL,
	`verified` VARCHAR(32) NULL DEFAULT NULL,
	`verify_message` VARCHAR(500) NULL DEFAULT NULL,
	`verify_time` DATETIME NULL DEFAULT NULL,
	`video_id` VARCHAR(255) NULL DEFAULT NULL,
	`type` VARCHAR(32) NULL DEFAULT NULL,
	`user_id` VARCHAR(32) NULL DEFAULT NULL,
	`verify_user_id` VARCHAR(32) NULL DEFAULT NULL,
	`max_invest_money` DOUBLE NULL DEFAULT NULL,
	`order_code` VARCHAR(50) NULL DEFAULT NULL,
	`transfer_type` TEXT NULL,
	`rate_iboi` DOUBLE NULL DEFAULT NULL,
	`publish_time` DATETIME NULL DEFAULT NULL,
	`business_product_id` VARCHAR(32) NULL DEFAULT NULL,
	`approval_id` VARCHAR(60) NULL DEFAULT NULL,
	`actual_give_money` DOUBLE NULL DEFAULT NULL,
	`real_loan_user` VARCHAR(500) NULL DEFAULT NULL COMMENT '真实借款人',
	`reveal_mess_relation_id` INT(11) NULL DEFAULT NULL,
	`credit_mess` VARCHAR(100) NULL DEFAULT NULL COMMENT '征信认证：credit,身份认证:identity,财务认证:finance,收入认证：income,实地认证：field,房产认证：house,车辆认证：car,设备认证:equipment,工作认证:work,企业认证:company',
	`trusteeship` VARCHAR(100) NULL DEFAULT NULL COMMENT '管理平台',
	`source` VARCHAR(100) NULL DEFAULT NULL COMMENT '来源',
	`procedure_fee` DOUBLE NULL DEFAULT NULL COMMENT '手续费收益',
	`migrate_status` VARCHAR(50) NULL DEFAULT NULL,
	`is_new` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是不是新标 0 不是 1是',
	`is_release_manually` tinyint(1) DEFAULT NULL COMMENT '是否手动开放 0不是 1是',
	`organization_id` BIGINT(20) NULL DEFAULT NULL,
	`person_loan_info_id` int(10) unsigned DEFAULT NULL COMMENT 'person_loan_info关联外键',
  `loan_approval_type_id` int(10) unsigned DEFAULT NULL COMMENT 'loan_approval_type关联外键',
	PRIMARY KEY (`primaryId`),
	UNIQUE INDEX `id` (`id`),
	UNIQUE INDEX `UK_9t7fe50lpkekqm37uj2l6i5sh` (`id`),
	INDEX `FK32C4F0D533E97D` (`verify_user_id`),
	INDEX `FK32C4F02A334343` (`user_id`),
	INDEX `FK32C4F0AC9D1931` (`business_product_id`),
	INDEX `FK_loan_loan_approval` (`approval_id`),
	INDEX `FK_loan_loan_extra` (`loan_extra_id`),
	INDEX `FK9_loan_reveal_mess_relation` (`reveal_mess_relation_id`),
	INDEX `IDX_BU_ID_PR` (`business_product_id`, `id`, `primaryId`),
	INDEX `FK_loan_organization` (`organization_id`),
	INDEX `FK_3rkn7uom77ta5kfb6aflmueqn` (`type`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE='utf8_general_ci' COMMENT ='标的表';

CREATE TABLE `user_bill` (
    `id` VARCHAR(32) NOT NULL,
    `detail` VARCHAR(200) NULL DEFAULT NULL,
    `money` DOUBLE NOT NULL,
    `seq_num` INT(11) NOT NULL,
    `time` DATETIME NOT NULL,
    `type_info` VARCHAR(100) NULL DEFAULT NULL,
    `user_id` VARCHAR(32) NOT NULL,
    `type` VARCHAR(200) NULL DEFAULT NULL,
    `balance` DOUBLE NULL DEFAULT NULL,
    `frozen_money` DOUBLE NULL DEFAULT NULL,
    `loan_id` VARCHAR(255) NULL DEFAULT NULL,
    `trusteeship` VARCHAR(100) NOT NULL COMMENT '存管平台标识',
    `actual_trusteeship` VARCHAR(100) NULL DEFAULT NULL COMMENT '实际存管平台',
    `jiangxi_bank_balance` DOUBLE NOT NULL DEFAULT '0' COMMENT '江西银行可用余额',
    `jiangxi_bank_frozen_money` DOUBLE NOT NULL DEFAULT '0' COMMENT '江西银行冻结金额',
    `project_name` VARCHAR(50) NULL DEFAULT '0' COMMENT '项目名称',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id` (`id`),
    INDEX `Index 4` (`user_id`, `type_info`),
    INDEX `IDX_TIME` (`time`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `trusteeship_account` (
	`id` VARCHAR(32) NOT NULL,
	`account_id` VARCHAR(500) NULL DEFAULT NULL,
	`create_time` DATETIME NULL DEFAULT NULL,
	`status` VARCHAR(500) NOT NULL,
	`trusteeship` VARCHAR(500) NOT NULL,
	`user_id` VARCHAR(32) NOT NULL,
	`id_type` VARCHAR(50) NULL DEFAULT NULL COMMENT '开户类型',
	`is_set_cash_password` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否设置交易密码。1表示已设置，0表示未设置',
	`jiangxi_acct_state` VARCHAR(1) NULL DEFAULT NULL COMMENT '账户状态。空-正常，A-待激活，Z-注销',
	`jiangxi_frz_state` VARCHAR(1) NULL DEFAULT NULL COMMENT '冻结状态。空-未冻结，J-司法冻结',
	`jiangxi_pin_los_cd` VARCHAR(1) NULL DEFAULT NULL COMMENT '密码挂失状态。空-未挂失，Q-已挂失',
	`obligate_bank_mobile_number` VARCHAR(18) NULL DEFAULT NULL COMMENT '银行预留手机号',
	`is_applied_set_cash_password` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否申请过设置交易密码。1表示已申请，0表示未申请',
	`init_password` VARCHAR(10) NULL DEFAULT NULL COMMENT '交易密码',
	`identity` VARCHAR(1) NULL DEFAULT NULL COMMENT '身份属性 1：出借角色  2：借款角色 3：代偿角色',
	`acct_use` VARCHAR(10) NULL DEFAULT '00000' COMMENT '账户用途 00000-普通账户  00100-担保账户  01000-手续费账户 10000-红包账户',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id` (`id`),
	INDEX `FK221B70222A334343` (`user_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `user_coupon` (
	`id` VARCHAR(32) NOT NULL,
	`deadline` DATETIME NULL DEFAULT NULL COMMENT '失效时间',
	`return_money` DOUBLE NULL DEFAULT NULL COMMENT '返还金额',
	`description` TEXT NULL,
	`generate_time` DATETIME NULL DEFAULT NULL,
	`status` VARCHAR(50) NOT NULL,
	`used_time` DATETIME NULL DEFAULT NULL,
	`coupon` VARCHAR(32) NOT NULL,
	`coupon_event_id` VARCHAR(32) NULL DEFAULT NULL COMMENT '营销方案id（标识用户的券从什么场景获得）',
	`user_id` VARCHAR(32) NULL DEFAULT NULL,
	`return_time` DATETIME NULL DEFAULT NULL COMMENT '返现时间',
	`invest` VARCHAR(32) NULL DEFAULT NULL,
	`user_project_plan_id` BIGINT(20) NULL DEFAULT NULL COMMENT '用户加入记录ID',
	`order_id` BIGINT(20) NULL DEFAULT NULL COMMENT '订单id',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id` (`id`),
	INDEX `FKDB7C1F1A2A334343` (`user_id`),
	INDEX `FKDB7C1F1A8501D293` (`coupon`),
	INDEX `FKDB7C1F1AF8D282C2` (`invest`),
	INDEX `FK_user_coupon_coupon_event_user_coupon` (`coupon_event_id`),
	INDEX `user_project_plan_id` (`user_project_plan_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `coupon` (
	`id` VARCHAR(32) NOT NULL,
	`identification_code` VARCHAR(10) NOT NULL COMMENT '优惠券识别码：LJ0001，XJ0001，FX0001，JX0001',
	`name` VARCHAR(100) NOT NULL COMMENT '优惠券名称',
	`generate_time` DATETIME NOT NULL COMMENT '生成时间',
	`create_user_id` VARCHAR(32) NOT NULL COMMENT '创建用户',
	`audit_user_id` VARCHAR(32) NULL DEFAULT NULL COMMENT '审核用户',
	`rate` DOUBLE(10,5) NULL DEFAULT NULL COMMENT '加息券的利率',
	`start_time` DATETIME NULL DEFAULT NULL COMMENT '开始时间',
	`status` VARCHAR(50) NOT NULL,
	`money` DOUBLE(10,2) NULL DEFAULT NULL,
	`gain_way` VARCHAR(100) NULL DEFAULT NULL COMMENT '获得方式描述',
	`deadline` DATETIME NULL DEFAULT NULL,
	`lower_limit_money` DOUBLE(10,2) NULL DEFAULT NULL COMMENT '最低限额',
	`higher_limit_money` DOUBLE(10,2) NULL DEFAULT NULL COMMENT '最高限额',
	`max_interest_money` DOUBLE(10,2) NULL DEFAULT NULL COMMENT '最高计息金额',
	`interest_deadline` INT(11) NULL DEFAULT NULL COMMENT '优惠券计息天数',
	`type` VARCHAR(200) NULL DEFAULT NULL,
	`period_of_validity` INT(11) NULL DEFAULT NULL COMMENT '有效期',
	`is_auto_used` INT(11) NULL DEFAULT NULL COMMENT '是否自动使用：1：是，0：不自动使用',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `identification_code` (`identification_code`),
	INDEX `FK_Reference_12` (`create_user_id`),
	INDEX `FK_coupon_user` (`audit_user_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


CREATE TABLE `config` (
	`id` VARCHAR(128) NOT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`value` TEXT NULL,
	`description` TEXT NULL,
	`type` VARCHAR(32) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_config_config_type` (`type`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `loan_trustee_pay_config` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`loan_id` VARCHAR(50) NOT NULL COMMENT '项目id',
	`receivable_user_id` VARCHAR(50) NOT NULL COMMENT '收款用户id',
	`repay_user_id` VARCHAR(50) NOT NULL COMMENT '还款用户id',
	`create_time` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	`update_time` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY (`id`),
	INDEX `FK__loan_trustee_pay_config` (`loan_id`)
)
COMMENT='项目受托支付配置表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `trustee_pay` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`real_borrow_user_id` VARCHAR(50) NOT NULL COMMENT '实际借款人',
	`nominal_borrow_user_id` VARCHAR(50) NOT NULL COMMENT '名义借款人（收款人）',
	`loan_primaryId` BIGINT(20) NOT NULL COMMENT '标的号',
	`money` DOUBLE NOT NULL COMMENT '借款金额',
	`status` VARCHAR(50) NOT NULL COMMENT '状态',
	`create_time` DATETIME NOT NULL COMMENT '创建时间',
	`update_time` DATETIME NULL DEFAULT NULL COMMENT '修改时间',
	`type` VARCHAR(50) NULL DEFAULT NULL COMMENT '类型：htouhui：海投汇，third_party：第三方资产',
	PRIMARY KEY (`id`),
	INDEX `FK_trustee_pay_user_2` (`nominal_borrow_user_id`),
	INDEX `FK_trustee_pay_user` (`real_borrow_user_id`),
	INDEX `FK2_trustee_pay_loan` (`loan_primaryId`)
)
COMMENT='受托支付表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `festival` (
	`id` TINYINT(4) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
	`festival_day` DATE NULL DEFAULT NULL COMMENT '节假日日期',
	`work_day` DATE NULL DEFAULT NULL COMMENT '法定工作日日期（周六周日等需要定为工作日）',
	`remark` VARCHAR(255) NULL DEFAULT NULL COMMENT '备注说明',
	PRIMARY KEY (`id`),
	INDEX `festival_day` (`festival_day`),
	INDEX `work_day` (`work_day`)
)
COMMENT='节假日及法定工作日表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=37
;

CREATE TABLE `loan_type` (
	`id` VARCHAR(64) NOT NULL,
	`interest_point` VARCHAR(200) NULL DEFAULT NULL,
	`interest_type` VARCHAR(200) NULL DEFAULT NULL,
	`name` VARCHAR(32) NULL DEFAULT NULL,
	`repay_time_period` INT(11) NULL DEFAULT NULL,
	`repay_time_unit` VARCHAR(200) NULL DEFAULT NULL,
	`repay_type` VARCHAR(200) NULL DEFAULT NULL,
	`is_use` TINYINT(1) NULL DEFAULT '1' COMMENT '是否使用',
	`description` TEXT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `system_bill` (
	`id` VARCHAR(32) NOT NULL,
	`time` DATETIME NOT NULL,
	`type` VARCHAR(32) NULL DEFAULT NULL,
	`money` DOUBLE NULL DEFAULT NULL,
	`detail` VARCHAR(200) NULL DEFAULT NULL,
	`seq_num` INT(11) NULL DEFAULT NULL,
	`reason` VARCHAR(200) NULL DEFAULT NULL,
	`trusteeship` VARCHAR(200) NOT NULL COMMENT '存管平台标识',
	`balance` DOUBLE NULL DEFAULT NULL,
	`jiangxi_bank_red_envlope_balance` DOUBLE NOT NULL DEFAULT '0' COMMENT '海投汇江西银行红包账号可用余额',
	`jiangxi_bank_fee_balance` DOUBLE NOT NULL DEFAULT '0' COMMENT '海投汇江西银行手续费账号可用余额',
	PRIMARY KEY (`id`),
	INDEX `IDX_SEQ_NUM` (`seq_num`)
)
COMMENT='直接给系统建一个用户，然后用用户的投资账户就行'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `qrtz_job_details` (
	`SCHED_NAME` VARCHAR(120) NOT NULL,
	`JOB_NAME` VARCHAR(200) NOT NULL,
	`JOB_GROUP` VARCHAR(200) NOT NULL,
	`DESCRIPTION` VARCHAR(250) NULL DEFAULT NULL,
	`JOB_CLASS_NAME` VARCHAR(250) NOT NULL,
	`IS_DURABLE` VARCHAR(1) NOT NULL,
	`IS_NONCONCURRENT` VARCHAR(1) NOT NULL,
	`IS_UPDATE_DATA` VARCHAR(1) NOT NULL,
	`REQUESTS_RECOVERY` VARCHAR(1) NOT NULL,
	`JOB_DATA` BLOB NULL,
	PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`),
	INDEX `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`, `REQUESTS_RECOVERY`),
	INDEX `IDX_QRTZ_J_GRP` (`SCHED_NAME`, `JOB_GROUP`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `qrtz_triggers` (
	`SCHED_NAME` VARCHAR(120) NOT NULL,
	`TRIGGER_NAME` VARCHAR(200) NOT NULL,
	`TRIGGER_GROUP` VARCHAR(200) NOT NULL,
	`JOB_NAME` VARCHAR(200) NOT NULL,
	`JOB_GROUP` VARCHAR(200) NOT NULL,
	`DESCRIPTION` VARCHAR(250) NULL DEFAULT NULL,
	`NEXT_FIRE_TIME` BIGINT(13) NULL DEFAULT NULL,
	`PREV_FIRE_TIME` BIGINT(13) NULL DEFAULT NULL,
	`PRIORITY` INT(11) NULL DEFAULT NULL,
	`TRIGGER_STATE` VARCHAR(16) NOT NULL,
	`TRIGGER_TYPE` VARCHAR(8) NOT NULL,
	`START_TIME` BIGINT(13) NOT NULL,
	`END_TIME` BIGINT(13) NULL DEFAULT NULL,
	`CALENDAR_NAME` VARCHAR(200) NULL DEFAULT NULL,
	`MISFIRE_INSTR` SMALLINT(2) NULL DEFAULT NULL,
	`JOB_DATA` BLOB NULL,
	PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
	INDEX `IDX_QRTZ_T_J` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`),
	INDEX `IDX_QRTZ_T_JG` (`SCHED_NAME`, `JOB_GROUP`),
	INDEX `IDX_QRTZ_T_C` (`SCHED_NAME`, `CALENDAR_NAME`),
	INDEX `IDX_QRTZ_T_G` (`SCHED_NAME`, `TRIGGER_GROUP`),
	INDEX `IDX_QRTZ_T_STATE` (`SCHED_NAME`, `TRIGGER_STATE`),
	INDEX `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`),
	INDEX `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`),
	INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`, `NEXT_FIRE_TIME`),
	INDEX `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`),
	INDEX `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`),
	INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`),
	INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `qrtz_blob_triggers` (
	`SCHED_NAME` VARCHAR(120) NOT NULL,
	`TRIGGER_NAME` VARCHAR(200) NOT NULL,
	`TRIGGER_GROUP` VARCHAR(200) NOT NULL,
	`BLOB_DATA` BLOB NULL,
	PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
	INDEX `SCHED_NAME` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `qrtz_calendars` (
	`SCHED_NAME` VARCHAR(120) NOT NULL,
	`CALENDAR_NAME` VARCHAR(200) NOT NULL,
	`CALENDAR` BLOB NOT NULL,
	PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `qrtz_cron_triggers` (
	`SCHED_NAME` VARCHAR(120) NOT NULL,
	`TRIGGER_NAME` VARCHAR(200) NOT NULL,
	`TRIGGER_GROUP` VARCHAR(200) NOT NULL,
	`CRON_EXPRESSION` VARCHAR(120) NOT NULL,
	`TIME_ZONE_ID` VARCHAR(80) NULL DEFAULT NULL,
	PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `qrtz_fired_triggers` (
	`SCHED_NAME` VARCHAR(120) NOT NULL,
	`ENTRY_ID` VARCHAR(95) NOT NULL,
	`TRIGGER_NAME` VARCHAR(200) NOT NULL,
	`TRIGGER_GROUP` VARCHAR(200) NOT NULL,
	`INSTANCE_NAME` VARCHAR(200) NOT NULL,
	`FIRED_TIME` BIGINT(13) NOT NULL,
	`SCHED_TIME` BIGINT(13) NOT NULL,
	`PRIORITY` INT(11) NOT NULL,
	`STATE` VARCHAR(16) NOT NULL,
	`JOB_NAME` VARCHAR(200) NULL DEFAULT NULL,
	`JOB_GROUP` VARCHAR(200) NULL DEFAULT NULL,
	`IS_NONCONCURRENT` VARCHAR(1) NULL DEFAULT NULL,
	`REQUESTS_RECOVERY` VARCHAR(1) NULL DEFAULT NULL,
	PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`),
	INDEX `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`, `INSTANCE_NAME`),
	INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`),
	INDEX `IDX_QRTZ_FT_J_G` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`),
	INDEX `IDX_QRTZ_FT_JG` (`SCHED_NAME`, `JOB_GROUP`),
	INDEX `IDX_QRTZ_FT_T_G` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`),
	INDEX `IDX_QRTZ_FT_TG` (`SCHED_NAME`, `TRIGGER_GROUP`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `qrtz_locks` (
	`SCHED_NAME` VARCHAR(120) NOT NULL,
	`LOCK_NAME` VARCHAR(40) NOT NULL,
	PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `qrtz_paused_trigger_grps` (
	`SCHED_NAME` VARCHAR(120) NOT NULL,
	`TRIGGER_GROUP` VARCHAR(200) NOT NULL,
	PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `qrtz_scheduler_state` (
	`SCHED_NAME` VARCHAR(120) NOT NULL,
	`INSTANCE_NAME` VARCHAR(200) NOT NULL,
	`LAST_CHECKIN_TIME` BIGINT(13) NOT NULL,
	`CHECKIN_INTERVAL` BIGINT(13) NOT NULL,
	PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `qrtz_simple_triggers` (
	`SCHED_NAME` VARCHAR(120) NOT NULL,
	`TRIGGER_NAME` VARCHAR(200) NOT NULL,
	`TRIGGER_GROUP` VARCHAR(200) NOT NULL,
	`REPEAT_COUNT` BIGINT(7) NOT NULL,
	`REPEAT_INTERVAL` BIGINT(12) NOT NULL,
	`TIMES_TRIGGERED` BIGINT(10) NOT NULL,
	PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `qrtz_simprop_triggers` (
	`SCHED_NAME` VARCHAR(120) NOT NULL,
	`TRIGGER_NAME` VARCHAR(200) NOT NULL,
	`TRIGGER_GROUP` VARCHAR(200) NOT NULL,
	`STR_PROP_1` VARCHAR(512) NULL DEFAULT NULL,
	`STR_PROP_2` VARCHAR(512) NULL DEFAULT NULL,
	`STR_PROP_3` VARCHAR(512) NULL DEFAULT NULL,
	`INT_PROP_1` INT(11) NULL DEFAULT NULL,
	`INT_PROP_2` INT(11) NULL DEFAULT NULL,
	`LONG_PROP_1` BIGINT(20) NULL DEFAULT NULL,
	`LONG_PROP_2` BIGINT(20) NULL DEFAULT NULL,
	`DEC_PROP_1` DECIMAL(13,4) NULL DEFAULT NULL,
	`DEC_PROP_2` DECIMAL(13,4) NULL DEFAULT NULL,
	`BOOL_PROP_1` VARCHAR(1) NULL DEFAULT NULL,
	`BOOL_PROP_2` VARCHAR(1) NULL DEFAULT NULL,
	PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `leo_user_payment_auth` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`user_id` VARCHAR(50) NOT NULL COMMENT '用户id',
	`max_amount` DOUBLE NOT NULL COMMENT '签约最大金额  单位：元，最多两位小数(个人25w，企业125w)',
	`deadline` DATE NOT NULL COMMENT '签约到期日',
	`create_time` DATETIME NOT NULL COMMENT '创建时间',
	`update_time` DATETIME NULL DEFAULT NULL COMMENT '更新时间',
	`status` VARCHAR(50) NOT NULL COMMENT '签约状态',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `user_id_unique` (`user_id`),
	INDEX `id` (`id`),
	INDEX `user_id` (`user_id`)
)
COMMENT='用户缴费授权表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2;

CREATE TABLE `fee_configuration` (
	`id` VARCHAR(32) NOT NULL,
	`fee_point` VARCHAR(400) NULL DEFAULT NULL,
	`fee_type` VARCHAR(400) NULL DEFAULT NULL,
	`factor` VARCHAR(400) NULL DEFAULT NULL,
	`factor_value` VARCHAR(400) NULL DEFAULT NULL,
	`fee` DOUBLE NULL DEFAULT NULL,
	`fee_upper_limit` DOUBLE NULL DEFAULT NULL,
	`interval_lower_limit` DOUBLE NULL DEFAULT NULL,
	`interval_upper_limit` DOUBLE NULL DEFAULT NULL,
	`description` VARCHAR(200) NULL DEFAULT NULL,
	`operate_mode` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `user_message_way` (
	`id` VARCHAR(32) NOT NULL,
	`description` VARCHAR(1000) NULL DEFAULT NULL,
	`name` VARCHAR(100) NOT NULL,
	`status` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
CREATE TABLE `user_message_node` (
	`id` VARCHAR(32) NOT NULL,
	`description` VARCHAR(200) NULL DEFAULT NULL,
	`name` VARCHAR(50) NOT NULL,
	`status` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
CREATE TABLE `user_message_template` (
	`id` VARCHAR(64) NOT NULL,
	`description` VARCHAR(1000) NULL DEFAULT NULL,
	`name` VARCHAR(200) NULL DEFAULT NULL,
	`status` VARCHAR(50) NOT NULL,
	`template` TEXT NOT NULL,
	`message_node` VARCHAR(32) NOT NULL,
	`message_way` VARCHAR(32) NULL DEFAULT NULL,
	`third_template_id` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id` (`id`),
	INDEX `FK6FEB3BA65FA2336D` (`message_node`),
	INDEX `FK6FEB3BA6D9CBAD55` (`message_way`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
CREATE TABLE `sms_config` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`sms_platform` VARCHAR(100) NOT NULL COMMENT '短信平台',
	`status` VARCHAR(50) NOT NULL COMMENT '状态',
	PRIMARY KEY (`id`),
	INDEX `id` (`id`)
)
COMMENT='短信平台配置表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4
;
CREATE TABLE `yimei_send_sms_record` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`cdkey` VARCHAR(50) NOT NULL COMMENT '账号',
	`password` VARCHAR(50) NOT NULL COMMENT '密码',
	`phone` VARCHAR(50) NOT NULL COMMENT '手机号',
	`message` TEXT NOT NULL COMMENT '短信内容',
	`addserial` VARCHAR(10) NULL DEFAULT NULL COMMENT '附加号',
	`smspriority` TINYINT(4) NULL DEFAULT NULL COMMENT '短信优先级',
	`status` VARCHAR(50) NULL DEFAULT NULL COMMENT '状态',
	PRIMARY KEY (`id`),
	INDEX `id` (`id`)
)
COMMENT='亿美短信记录'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=87;


CREATE TABLE `node` (
	`id` VARCHAR(32) NOT NULL,
	`node_type` VARCHAR(32) NOT NULL,
	`title` VARCHAR(100) NULL DEFAULT NULL,
	`subtitle` VARCHAR(100) NULL DEFAULT NULL,
	`body` VARCHAR(32) NULL DEFAULT NULL,
	`language` VARCHAR(10) NOT NULL DEFAULT '*',
	`status` VARCHAR(20) NOT NULL,
	`keywords` TEXT NULL,
	`description` TEXT NULL,
	`create_time` DATETIME NOT NULL,
	`update_time` DATETIME NOT NULL,
	`version` DOUBLE NULL DEFAULT NULL,
	`creator` VARCHAR(32) NOT NULL,
	`last_modify_user` VARCHAR(32) NULL DEFAULT NULL,
	`seq_num` INT(11) NULL DEFAULT NULL,
	`thumb` VARCHAR(80) NULL DEFAULT NULL,
	`source_link` VARCHAR(200) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_node_creator` (`creator`),
	INDEX `FK_node_lasteat_modify_user` (`last_modify_user`),
	INDEX `FK_node_node_body` (`body`),
	INDEX `FK_node_nodeType` (`node_type`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `node_body` (
	`id` VARCHAR(32) NOT NULL,
	`body` LONGTEXT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `leo_payment_authorization` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(50) NOT NULL COMMENT '用户id',
  `max_payment_money` DECIMAL(16,4) NOT NULL COMMENT '最大签约金额',
  `status` TINYINT(4) NOT NULL COMMENT '还款授权签约状态（0:待签约,10:已签约,20:签约失败,30:签约过期,40:撤销签约）',
  `deadline` DATE NOT NULL COMMENT '签约到期日期',
  `create_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_id_unique` (`user_id`),
  INDEX `id` (`id`),
  INDEX `user_id` (`user_id`)
)
COMMENT='缴费授权表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `esign_user` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`user_id` VARCHAR(50) NOT NULL DEFAULT '0' COMMENT '用户id',
	`account_id` VARCHAR(50) NOT NULL COMMENT 'e签宝账户标识',
	`status` VARCHAR(50) NULL DEFAULT NULL COMMENT '状态',
	`create_time` DATETIME NOT NULL COMMENT '创建时间',
	`seal_data` TEXT NULL COMMENT '电子印章图片Base64数据',
	PRIMARY KEY (`id`),
	INDEX `FK1_esign_user_id` (`user_id`)
)
COMMENT='e签宝用户表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `invest` (
	`id` VARCHAR(32) NOT NULL,
	`interest` DOUBLE NULL DEFAULT NULL,
	`money` DOUBLE NOT NULL,
	`paid_interest` DECIMAL(10,2) NULL DEFAULT '0.00',
	`paid_money` DECIMAL(10,2) NULL DEFAULT '0.00',
	`rate` DOUBLE NULL DEFAULT NULL,
	`status` VARCHAR(50) NOT NULL,
	`time` DATETIME NOT NULL,
	`type` VARCHAR(100) NULL DEFAULT NULL,
	`loan_id` VARCHAR(32) NOT NULL,
	`invest_version` VARCHAR(10) NOT NULL DEFAULT '1.0',
	`user_id` VARCHAR(32) NOT NULL,
	`is_auto_invest` TINYINT(1) NULL DEFAULT NULL,
	`paid_penalty` DECIMAL(10,2) NULL DEFAULT '0.00',
	`user_coupon` VARCHAR(32) NULL DEFAULT NULL,
	`invest_money` DOUBLE NULL DEFAULT NULL,
	`transfer_apply` VARCHAR(32) NULL DEFAULT NULL,
	`extend_benefits` DOUBLE(10,2) NULL DEFAULT '0.00' COMMENT '额外收益',
	`expect_extend_benefits` DOUBLE(10,2) NULL DEFAULT '0.00' COMMENT '预期额外收益',
	`source` VARCHAR(50) NULL DEFAULT NULL COMMENT '定期项目投资行为来源',
	`fee` DOUBLE(10,2) NULL DEFAULT '0.00' COMMENT '每笔投资带来的手续费收入',
	`cpsCost` DOUBLE(10,2) NULL DEFAULT '0.00' COMMENT 'cps渠道成本',
	`interval_days` INT(11) NULL DEFAULT '0' COMMENT '放款时间和投资时间间隔-1',
	`interval_interest` DOUBLE(10,2) NULL DEFAULT '0.00' COMMENT '即投即生息天数产生的收益',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id` (`id`),
	INDEX `FKB9721FF52A334343` (`user_id`),
	INDEX `FKB9721FF5ECD93B37` (`loan_id`),
	INDEX `FKB9721FF58A5331B2` (`user_coupon`),
	INDEX `FKB9721FF541E23285` (`transfer_apply`),
	INDEX `IDX_TIME` (`time`),
	INDEX `IDX_LOAN_ID_USER_ID` (`loan_id`, `user_id`),
	INDEX `status` (`status`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
CREATE TABLE `invest_repay` (
	`id` VARCHAR(32) NOT NULL,
	`corpus` DOUBLE NOT NULL,
	`default_interest` DOUBLE NULL DEFAULT NULL,
	`interest` DOUBLE NOT NULL,
	`length` INT(11) NULL DEFAULT NULL,
	`period` INT(11) NOT NULL,
	`repay_day` DATETIME NOT NULL,
	`status` VARCHAR(50) NOT NULL,
	`time` DATETIME NULL DEFAULT NULL,
	`invest_id` VARCHAR(32) NOT NULL,
	`platform_tiexi_rate` DOUBLE(5,2) NULL DEFAULT '0.00' COMMENT '平台贴息利率',
	`platform_tiexi_money` DOUBLE(5,2) NULL DEFAULT '0.00' COMMENT '平台贴息金额',
	`user_tiexi_rate` DOUBLE(5,2) NULL DEFAULT '0.00' COMMENT '用户贴息利率',
	`user_tiexi_money` DOUBLE(5,2) NULL DEFAULT '0.00' COMMENT '用户贴息金额',
	`fee` DOUBLE NULL DEFAULT NULL,
	`loan_user_fee` DOUBLE(8,2) NULL DEFAULT '0.00' COMMENT '当期借款还款总手续费，平均当期到每一笔投资还款的值。',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id` (`id`),
	INDEX `FK860808CBC736B472` (`invest_id`),
	INDEX `status` (`status`),
	INDEX `repay_day` (`repay_day`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `organization` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(30) NULL DEFAULT NULL COMMENT '邮箱',
	`mobile` VARCHAR(11) NOT NULL COMMENT '手机号',
	`organization_name` VARCHAR(50) NOT NULL COMMENT '机构名称',
	`organization_type` INT(11) NULL DEFAULT '0' COMMENT '单位类型，0-普通企业，1-社会团体，2-事业单位，3-民办非企业单位，4-党政及国家机构',
	`register_type` INT(11) NULL DEFAULT '0' COMMENT '注册类型，1-代理人注册，2-法人注册，默认1',
	`reg_code` VARCHAR(100) NULL DEFAULT NULL COMMENT '工商注册号',
	`organization_code` VARCHAR(50) NOT NULL COMMENT '组织机构代码号或社会信用代码号',
	`legal_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '法定代表姓名',
	`legal_id_card` VARCHAR(50) NULL DEFAULT NULL COMMENT '法定代表身份证号/护照号',
	`legal_area` VARCHAR(10) NULL DEFAULT NULL COMMENT '法定代表人归属地："0"-大陆，"1"-香港，"2"-澳门，"3"-台湾，"4"-外籍，默认"0"',
	`agent_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '代理人姓名',
	`agent_id_card` VARCHAR(50) NULL DEFAULT NULL COMMENT '代理人身份证号',
	`company_address` VARCHAR(100) NULL DEFAULT NULL COMMENT '公司地址',
	`scope` VARCHAR(100) NULL DEFAULT NULL COMMENT '经营范围',
	`company_type` INT(11) NOT NULL COMMENT '公司类型，1：担保放，0：回购方',
	`account_id` VARCHAR(100) NULL DEFAULT NULL COMMENT 'e签宝唯一标识',
	`seal_data` TEXT NULL COMMENT '电子印章图片Base64数据',
	PRIMARY KEY (`id`),
	INDEX `id` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `yeepay_corp_info` (
	`id` VARCHAR(32) NOT NULL,
	`bank_license` VARCHAR(255) NULL DEFAULT NULL,
	`business_license` VARCHAR(255) NULL DEFAULT NULL,
	`contact` VARCHAR(255) NULL DEFAULT NULL,
	`contact_phone` VARCHAR(255) NULL DEFAULT NULL,
	`email` VARCHAR(255) NULL DEFAULT NULL,
	`enterprise_name` VARCHAR(255) NULL DEFAULT NULL,
	`legal` VARCHAR(255) NULL DEFAULT NULL,
	`legal_id_no` VARCHAR(255) NULL DEFAULT NULL,
	`member_class_type` VARCHAR(255) NULL DEFAULT NULL,
	`org_no` VARCHAR(255) NULL DEFAULT NULL,
	`seq` INT(11) NULL DEFAULT NULL,
	`status` VARCHAR(255) NULL DEFAULT NULL,
	`tax_no` VARCHAR(255) NULL DEFAULT NULL,
	`user_id` VARCHAR(32) NULL DEFAULT NULL,
	`bank_account_licence_picUrl` VARCHAR(255) NULL DEFAULT NULL,
	`business_license_picUrl` VARCHAR(255) NULL DEFAULT NULL,
	`idcardof_legalperson_picUrl` VARCHAR(255) NULL DEFAULT NULL,
	`organization_code_certificate_picUrl` VARCHAR(255) NULL DEFAULT NULL,
	`tax_registration_certificate_picUrl` VARCHAR(255) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id` (`id`),
	INDEX `FK2D29F7132A334343` (`user_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `user_login_log` (
	`id` VARCHAR(32) NOT NULL,
	`is_success` VARCHAR(1) NOT NULL,
	`login_info` VARCHAR(200) NULL DEFAULT NULL,
	`login_ip` VARCHAR(70) NULL DEFAULT NULL,
	`login_time` DATETIME NOT NULL,
	`username` VARCHAR(100) NOT NULL,
	`user_id` VARCHAR(32) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id` (`id`),
	INDEX `FKF6F2C9FA2A334343` (`user_id`),
	INDEX `IDX_USERNAME` (`username`),
	INDEX `IDX_USERNAME_LOGIN_TIME` (`username`, `login_time`),
	INDEX `IDX_LOGIN_TIME` (`login_time`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
CREATE TABLE `auth_info` (
	`id` VARCHAR(32) NOT NULL DEFAULT '',
	`auth_code` VARCHAR(1000) NULL DEFAULT NULL,
	`auth_target` VARCHAR(1000) NULL DEFAULT NULL,
	`deadline` DATETIME NULL DEFAULT NULL,
	`generation_time` DATETIME NULL DEFAULT NULL,
	`status` VARCHAR(100) NULL DEFAULT NULL,
	`auth_type` VARCHAR(200) NOT NULL,
	`auth_source` VARCHAR(1000) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
CREATE TABLE `trusteeship_auto_bid_auth` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
	`user_id` VARCHAR(32) NOT NULL COMMENT '用户id',
	`order_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '自动投标签约订单号',
	`trusteeship` VARCHAR(50) NULL DEFAULT NULL COMMENT '平台',
	`create_time` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	`complete_time` DATETIME NULL DEFAULT NULL COMMENT '完成时间',
	`tx_amount` DECIMAL(12,2) NULL DEFAULT NULL COMMENT '单笔投标金额的上限',
	`total_amount` DECIMAL(12,2) NULL DEFAULT NULL COMMENT '自动投标总金额上限',
	`status` VARCHAR(30) NULL DEFAULT NULL COMMENT '状态 待签约 已签约 待撤销 已撤销',
	`times` INT(7) NULL DEFAULT NULL COMMENT '主动查询失败次数',
	`deadline` DATE NULL DEFAULT NULL COMMENT '到期日期',
	PRIMARY KEY (`id`),
	UNIQUE INDEX `user_id` (`user_id`),
	INDEX `fk_auto_user` (`user_id`),
	INDEX `status` (`status`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;

CREATE TABLE `coupon_limit_rule` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`coupon_id` VARCHAR(50) NOT NULL DEFAULT '0',
	`type` VARCHAR(50) NOT NULL DEFAULT '0' COMMENT '项目类型',
	`min_period` INT(11) NULL DEFAULT '0' COMMENT '优惠券用于直投的区间下限',
	`max_period` INT(11) NULL DEFAULT '0' COMMENT '优惠券用于直投的区间上限',
	`product_id` BIGINT(20) NULL DEFAULT NULL,
	`create_time` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (`id`),
	INDEX `FK_coupon_limit_rule_coupon` (`coupon_id`),
	INDEX `FK_coupon_limit_rule_shengxinbao_plan` (`product_id`)
)
COMMENT='优惠券限制规则表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
CREATE TABLE `user_role` (
	`user_id` VARCHAR(32) NOT NULL,
	`role_id` VARCHAR(32) NOT NULL,
	PRIMARY KEY (`user_id`, `role_id`),
	INDEX `FK_user_role_role` (`role_id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
CREATE TABLE `risk_assessment_result` (
	`id` VARCHAR(50) NOT NULL,
	`score` VARCHAR(50) NULL DEFAULT NULL COMMENT '用户分值',
	`assessment_time` DATETIME NULL DEFAULT NULL COMMENT '完成评测时间',
	`status` VARCHAR(50) NULL DEFAULT NULL COMMENT '状态,assessing代表参加了测评,但是没完成.assessed代表完成测评',
	`questionnaire_warehouse_id` VARCHAR(50) NOT NULL,
	`questionnaire_subject_id` VARCHAR(50) NULL DEFAULT NULL,
	`user_id` VARCHAR(50) NOT NULL,
	`rt_option` VARCHAR(500) NOT NULL,
	`risk_level` VARCHAR(50) NULL DEFAULT NULL COMMENT '风险等级',
	`risk_type` VARCHAR(50) NULL DEFAULT NULL COMMENT '风险类型',
	`risk_quote` VARCHAR(50) NULL DEFAULT NULL COMMENT '风险评语',
	`tip_max_invest_money` DOUBLE NULL DEFAULT NULL COMMENT '建议单笔最高投资额',
	PRIMARY KEY (`id`),
	INDEX `FK_risk_assessment_result_user` (`user_id`),
	INDEX `FK_risk_assessment_result_questionnaire_warehouse` (`questionnaire_warehouse_id`),
	INDEX `FK_risk_assessment_result_questionnaire_subject` (`questionnaire_subject_id`)
)
COMMENT='用户风险评估结果表'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `wai_xiao_quan` (
	`id` VARCHAR(32) NOT NULL,
	`cdkey` VARCHAR(50) NOT NULL COMMENT '激活码',
	`waixiaofangan_id` VARCHAR(32) NOT NULL,
	`user_coupon_id` VARCHAR(32) NULL DEFAULT NULL COMMENT '用户优惠券，激活才有',
	`status` VARCHAR(10) NOT NULL COMMENT 'init：初始化；actived：已激活；expire：超期',
	`create_time` DATETIME NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `cdkey` (`cdkey`),
	INDEX `FK__wai_xiao_quan_waixiaofangan_id` (`waixiaofangan_id`),
	INDEX `FK_wai_xiao_quan_user_coupon` (`user_coupon_id`)
)
COMMENT='外销券'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `wai_xiao_fang_an` (
	`id` VARCHAR(32) NOT NULL,
	`coupon_event_id` INT(10) NOT NULL DEFAULT '11' COMMENT '外销场景id',
	`name` VARCHAR(100) NOT NULL COMMENT '方案名称',
	`coupon_id` VARCHAR(32) NOT NULL,
	`create_user_id` VARCHAR(32) NOT NULL,
	`audit_user_id` VARCHAR(32) NULL DEFAULT NULL,
	`count` INT(10) NOT NULL DEFAULT '0' COMMENT '数量',
	`start_time` DATETIME NULL DEFAULT NULL,
	`end_time` DATETIME NULL DEFAULT NULL,
	`create_time` DATETIME NOT NULL COMMENT 'init：待审核；enable：可用；disable：不可用；refuse：审核拒绝',
	`status` VARCHAR(10) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK__waixiaofangan_coupon_id` (`coupon_id`),
	INDEX `FK_wai_xiao_fang_an_user` (`create_user_id`),
	INDEX `FK_wai_xiao_fang_an_user_2` (`audit_user_id`),
	INDEX `FK_wai_xiao_fang_an_coupon_event` (`coupon_event_id`)
)
COMMENT='外销方案'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

/******************************************/
/*   数据库全名 = htouhui_db   */
/*   表名称 = area   */
/******************************************/
CREATE TABLE `area` (
	`id` varchar(32) NOT NULL,
	`name` varchar(50) NOT NULL,
	`pid` varchar(32) DEFAULT NULL,
	`comments` text,
	`seq_num` int(11) DEFAULT NULL,
	`first_letter` varchar(1) DEFAULT NULL,
	PRIMARY KEY (`id`),
	KEY `FK2DD08D32F0EB81` (`pid`) USING BTREE,
	CONSTRAINT `area_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `area` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

/******************************************/
/*   数据库全名 = htouhui_db   */
/*   表名称 = shengxinbao_plan   */
/******************************************/
CREATE TABLE `shengxinbao_plan` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`business_product_id` varchar(32) NOT NULL,
	`plan_name` varchar(32) NOT NULL COMMENT '计划名称',
	`plan_type` varchar(20) NOT NULL COMMENT '计划类型',
	`lock_period` int(11) DEFAULT NULL COMMENT '锁定期',
	`unit` varchar(10) DEFAULT NULL COMMENT '锁定期单位',
	`rate` double DEFAULT NULL COMMENT '预计年化利率',
	`min_rate` double DEFAULT NULL COMMENT '浮动利率:最低利率',
	`max_rate` double DEFAULT NULL COMMENT '浮动利率:最高利率',
	`start_invest_money` decimal(15,2) DEFAULT NULL COMMENT '起投金额',
	`incr_money` decimal(15,2) DEFAULT NULL COMMENT '递增金额',
	`start_time` datetime DEFAULT NULL COMMENT '开始时间',
	`end_time` datetime DEFAULT NULL COMMENT '结束时间',
	`apply_user_id` varchar(32) NOT NULL COMMENT '申请人',
	`status` varchar(50) DEFAULT NULL COMMENT '状态 可加入 已售完 已过期',
	`web_show` tinyint(4) DEFAULT NULL COMMENT 'app是否展示',
	`app_show` tinyint(4) DEFAULT NULL COMMENT 'web是否展示',
	`promotion_position_of_app` int(11) DEFAULT NULL COMMENT 'app推广顺序',
	`match_deadline` varchar(100) DEFAULT NULL COMMENT '可匹配债权期限',
	`plan_desc` varchar(100) NOT NULL COMMENT '计划描述',
	`type` varchar(30) NOT NULL COMMENT '类型(新手标/升薪宝)',
	`join_money_limit` double(15,2) DEFAULT NULL COMMENT '加入金额限制',
	`warning_money` double(15,2) DEFAULT NULL COMMENT '预警金额',
	`tiexi_rate` double DEFAULT NULL COMMENT '贴息利率',
	`tiexi_period` int(11) DEFAULT NULL COMMENT '贴息期限',
	`tiexi_period_unit` varchar(20) DEFAULT NULL COMMENT '贴息单位',
	`tiexi_begin_time` datetime DEFAULT NULL COMMENT '贴息开始时间',
	`tiexi_end_time` datetime DEFAULT NULL COMMENT '贴息结束时间',
	`exit_fee_rate` double DEFAULT NULL COMMENT '退出手续费利率',
	`is_tiexi` tinyint(4) NOT NULL,
	`purpose` varchar(50) DEFAULT NULL COMMENT '用途',
	`contract_type` varchar(50) DEFAULT NULL COMMENT '合同类型',
	`is_customization` tinyint(4) NOT NULL COMMENT '是否定制',
	`customization_join_user_id` varchar(500) DEFAULT NULL COMMENT '可加入定制用户名,多个用逗号隔开',
	`customization_buy_user_id` varchar(500) DEFAULT NULL COMMENT '购买定制退出用户名,多个用逗号隔开',
	`lock_transfer_limit` int(11) DEFAULT NULL COMMENT '锁定期内债转天数限制',
	`unlock_transfer_limit` int(11) DEFAULT NULL COMMENT '锁定期外债转天数限制',
	`labelPictureUrl` varchar(200) DEFAULT NULL COMMENT '升薪宝标签图片',
	`full_loan_user_id` varchar(50) DEFAULT NULL COMMENT '满标账号',
	`reinvest_type` varchar(50) DEFAULT NULL COMMENT '复投类型',
	`can_use_coupon` tinyint(4) DEFAULT NULL COMMENT '是否可使用优惠券，1：是，0：否',
	`is_can_appointment_exit` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否可预约退出',
	`is_can_ahead_exit` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否可提前退出',
	`app_describe` varchar(50) DEFAULT NULL COMMENT '客户端描述',
	`app_details` varchar(100) DEFAULT NULL COMMENT '客户端描述详情',
	`detail_lable_pic_url` varchar(100) DEFAULT NULL COMMENT '详情页标签图片',
	`no_reinvest_user_id` varchar(200) DEFAULT NULL COMMENT '不复投用户',
	`is_begin_on_join_interest` tinyint(4) DEFAULT '0' COMMENT '是否支持即投即生息',
	`lock_exit_without_fee_user_id` varchar(200) DEFAULT NULL COMMENT '锁定期内退出不收取手续费用户',
	`sticky_post` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶。1为置顶，0为不置顶',
	`sticky_position` int(11) NOT NULL DEFAULT '0' COMMENT '置顶位置序号，越小越靠前',
	`genre` varchar(50) DEFAULT NULL COMMENT '子类型',
	PRIMARY KEY (`id`),
	KEY `fk_new_shengxinbao_plan_business_product_` (`business_product_id`),
	KEY `fk_new_shengxinbao_plan_user` (`apply_user_id`),
	KEY `web_show_app_show_promotion_position_of_app` (`web_show`,`app_show`,`promotion_position_of_app`),
	CONSTRAINT `fk_new_shengxinbao_plan_business_product_` FOREIGN KEY (`business_product_id`) REFERENCES `business_product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
	CONSTRAINT `fk_new_shengxinbao_plan_user` FOREIGN KEY (`apply_user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='升薪宝计划表'
;

/******************************************/
/*   数据库全名 = htouhui_db   */
/*   表名称 = role   */
/******************************************/
CREATE TABLE `role` (
	`id` varchar(32) NOT NULL,
	`name` varchar(100) NOT NULL,
	`description` text,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
;

/******************************************/
/*   数据库全名 = htouhui_db   */
/*   表名称 = answer_warehouse   */
/******************************************/
CREATE TABLE `answer_warehouse` (
	`id` varchar(50) NOT NULL,
	`name` varchar(50) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='答案库,或者叫参考库'
;

/******************************************/
/*   数据库全名 = htouhui_db   */
/*   表名称 = risk_assessment_level   */
/******************************************/
CREATE TABLE `risk_assessment_level` (
	`id` varchar(50) NOT NULL,
	`type` varchar(50) DEFAULT NULL,
	`name` varchar(50) NOT NULL,
	`detail` varchar(200) NOT NULL,
	`min_score` int(11) DEFAULT NULL,
	`max_score` int(11) DEFAULT NULL,
	`tip_money` double NOT NULL,
	`answer_warehouse_id` varchar(50) NOT NULL COMMENT '答案库refer id',
	PRIMARY KEY (`id`),
	KEY `FK_risk_assessment_level_answer_warehouse` (`answer_warehouse_id`),
	CONSTRAINT `FK_risk_assessment_level_answer_warehouse` FOREIGN KEY (`answer_warehouse_id`) REFERENCES `answer_warehouse` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风险评估结果标准表'
;

/******************************************/
/*   数据库全名 = htouhui_db   */
/*   表名称 = auto_repay_config   */
/******************************************/
CREATE TABLE `auto_repay_config` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
	`user_id` varchar(32) NOT NULL COMMENT '借款人Id',
	`is_open` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否打开自动还款，1：打开，0：关闭，默认关闭',
	`source` varchar(50) DEFAULT NULL COMMENT '来源',
	PRIMARY KEY (`id`),
	UNIQUE KEY `arc_user_id` (`user_id`),
	KEY `user_id` (`user_id`),
	KEY `id` (`id`),
	CONSTRAINT `FK__borrower` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8 COMMENT='自动还款配置表'
;



ALTER TABLE `invest_repay`
	ADD COLUMN `repay_order_id` VARCHAR(50) NULL COMMENT '还款时给即信的订单号' AFTER `loan_user_fee`;
ALTER TABLE `loan`
	ADD COLUMN `lend_order_id` VARCHAR(60) NULL DEFAULT NULL COMMENT '放款订单号' AFTER `approval_id`;
ALTER TABLE `invest`
	ADD COLUMN `lend_order_id` VARCHAR(50) NULL COMMENT '放款订单号' AFTER `interval_interest`;

CREATE TABLE `bank_card` (
	`id` VARCHAR(32) NOT NULL,
	`name` VARCHAR(100) NULL DEFAULT NULL,
	`bank` VARCHAR(100) NULL DEFAULT NULL,
	`bank_area` VARCHAR(32) NULL DEFAULT NULL,
	`card_no` VARCHAR(100) NULL DEFAULT NULL,
	`time` DATETIME NULL DEFAULT NULL,
	`status` VARCHAR(50) NULL DEFAULT NULL,
	`user_id` VARCHAR(32) NULL DEFAULT NULL,
	`bindingprice` DOUBLE NULL DEFAULT NULL,
	`account_name` VARCHAR(200) NULL DEFAULT NULL,
	`bank_card_type` VARCHAR(100) NULL DEFAULT NULL,
	`bank_city` VARCHAR(100) NULL DEFAULT NULL,
	`bank_no` VARCHAR(128) NULL DEFAULT NULL,
	`bank_province` VARCHAR(100) NULL DEFAULT NULL,
	`binding_price` DOUBLE NULL DEFAULT NULL,
	`bank_address` VARCHAR(32) NULL DEFAULT NULL,
	`bank_service_type` VARCHAR(100) NULL DEFAULT NULL,
	`bankCardUrl` VARCHAR(255) NULL DEFAULT NULL,
	`card_bank_cnaps` VARCHAR(20) NULL DEFAULT NULL COMMENT '绑定银行联行号 人民银行分配的12位联行号',
	`trusteeship` VARCHAR(20) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_Reference_14` (`user_id`),
	INDEX `FK9571B333C7E886E7` (`bank_address`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;