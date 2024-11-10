-- 初始化SQL脚本

-- 用户表
create table if not exists users
(
    id          bigint primary key auto_increment comment '用户id',
    username    varchar(30)  not null unique comment '用户名',
    email       varchar(320) not null unique comment '用户邮箱',
    password    char(32)     not null comment 'MD5加密后的密码',
    salt        varchar(10)  not null comment '加密随机盐值',
    role        tinyint      not null default 2 comment '用户角色。1管理员 2普通用户',
    status      tinyint      not null default 1 comment '用户状态。1正常 2封禁',
    create_time datetime     not null comment '创建时间',
    update_time datetime     not null comment '修改时间',
    is_deleted  bit(1)       not null default 0 comment '是否被删除',
    unique index idx_username (username),
    unique index idx_email (email)
) comment '用户表';

-- 用户信息表
create table if not exists user_info
(
    id          bigint primary key auto_increment comment '用户信息id',
    user_id     bigint unique not null comment '用户id',
    nickname    varchar(10) comment '用户昵称',
    avatar_url  varchar(128) comment '头像url',
    create_time datetime      not null comment '创建时间',
    update_time datetime      not null comment '修改时间',
    is_deleted  bit(1)        not null default 0 comment '是否被删除',
    foreign key fk_user_id (user_id) references users (id),
    unique index idx_user_id (user_id)
) comment '用户信息表';

-- 用户余额表
create table if not exists balances
(
    id           bigint primary key auto_increment comment '用户余额id',
    user_id      bigint unique  not null comment '用户id',
    balance      decimal(15, 4) not null default 0 comment '余额。单位为元',
    free_balance decimal(15, 4) not null default 0 comment '免费接口余额。每日0点刷新，时区为北京时间',
    refresh_date date comment '上次刷新免费接口余额的日期',
    create_time  datetime       not null comment '创建时间',
    update_time  datetime       not null comment '修改时间',
    is_deleted   bit(1)         not null default 0 comment '是否被删除',
    foreign key fk_user_id (user_id) references users (id),
    unique index idx_user_id (user_id),
    index idx_balance (balance),
    index idx_free_balance (free_balance)
) comment '用户余额表';

-- 登录记录表
create table if not exists login_records
(
    id          bigint primary key auto_increment comment '登录记录id',
    user_id     bigint              not null comment '用户id',
    token       varchar(100) unique not null comment '登录token',
    ip          varchar(45) comment '登录ip。可用于获取登录所在地',
    user_agent  varchar(200) comment '登录时的UA。可用于获取登录设备信息',
    expire_time datetime            not null comment '登录信息过期时间。不存在永不过期的登录信息',
    is_offline  bit(1)              not null default 0 comment '是否下线',
    role        tinyint             not null comment '登录角色。1管理员 2普通用户',
    create_time datetime            not null comment '创建时间',
    update_time datetime            not null comment '修改时间',
    is_deleted  bit(1)              not null default 0 comment '是否被删除',
    foreign key fk_user_id (user_id) references users (id),
    index idx_user_id (user_id),
    index idx_token (token(10)),
    index idx_expire_time (expire_time),
    index idx_create_time (create_time)
) comment '登录记录表';

-- 支付方式表
create table if not exists pay_methods
(
    id          bigint primary key auto_increment comment '支付方式id',
    name        varchar(10)    not null comment '支付方式名称',
    currency    varchar(10)    not null default 'CNY' comment '支付货币。用英文缩写',
    rate        decimal(15, 4) not null default 1 comment '充值倍率。充值1单位货币可以获得多少余额',
    create_time datetime       not null comment '创建时间',
    update_time datetime       not null comment '修改时间',
    is_deleted  bit(1)         not null default 0 comment '是否被删除'
) comment '支付方式表';

-- 订单表
create table if not exists orders
(
    id            bigint primary key auto_increment comment '订单id',
    user_id       bigint         not null comment '用户id',
    pay_method_id bigint         not null comment '支付方式id',
    amount        decimal(15, 4) not null comment '支付金额',
    balance       decimal(15, 4) not null comment '到账余额',
    status        tinyint        not null comment '订单状态。1待支付 2已支付 3已取消',
    pay_time      datetime comment '支付时间',
    create_time   datetime       not null comment '创建时间',
    update_time   datetime       not null comment '修改时间',
    is_deleted    bit(1)         not null default 0 comment '是否被删除',
    foreign key fk_user_id (user_id) references users (id),
    foreign key fk_pay_method_id (pay_method_id) references pay_methods (id),
    index idx_user_id (user_id),
    index idx_balance (balance),
    index idx_pay_time (pay_time),
    index idx_create_time (create_time)
) comment '订单表';

-- 用户api key表
create table if not exists api_keys
(
    id          bigint primary key auto_increment comment '用户api key id',
    user_id     bigint      not null comment '用户id。一个用户可以有多个api key',
    secret_id   char(50)    not null unique comment '密钥id。一个32字节、36进制整数',
    secret_key  char(32)    not null comment 'MD5加密后的密钥',
    salt        varchar(10) not null comment 'secret_key加密随机盐值',
    is_enabled  bit(1)      not null default 1 comment '是否启用',
    expire_time datetime comment '过期时间。null表示永不过期',
    only_free   bit(1)      not null default 0 comment '是否仅限免费接口',
    note        varchar(100) comment '备注',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '修改时间',
    is_deleted  bit(1)      not null default 0 comment '是否被删除',
    foreign key fk_user_id (user_id) references users (id),
    index idx_user_id (user_id),
    unique index idx_secret_id (secret_id)
) comment '用户api key表';

-- api接口表
create table if not exists apis
(
    id              bigint primary key auto_increment comment 'api接口id',
    method          varchar(10)    not null comment '请求方式',
    path            varchar(100)   not null comment '请求路径。不能使用路径参数',
    name            varchar(20)    not null comment '接口名称',
    description     text comment '接口描述。markdown格式，会展示在接口文档中',
    is_enabled      bit(1)         not null default 1 comment '是否可用',
    is_free         bit(1)         not null comment '是否为免费接口',
    consume_balance decimal(15, 4) not null comment '每次调用消耗余额',
    create_time     datetime       not null comment '创建时间',
    update_time     datetime       not null comment '修改时间',
    is_deleted      bit(1)         not null default 0 comment '是否被删除',
    unique index idx_method_path (method, path)
) comment 'api接口表';

-- api调用记录表
create table if not exists api_records
(
    id              bigint primary key auto_increment comment 'api调用记录id',
    key_id          bigint         not null comment 'api key id',
    api_id          bigint         not null comment 'api id',
    consume_balance decimal(15, 4) not null comment '消耗余额',
    ip              varchar(45)    not null comment '调用者ip',
    err             varchar(256) comment '调用错误信息。null表示调用成功',
    create_time     datetime       not null comment '创建时间。接口调用时间',
    update_time     datetime       not null comment '修改时间',
    is_deleted      bit(1)         not null default 0 comment '是否被删除',
    foreign key fk_key_id (key_id) references api_keys (id),
    foreign key fk_api_id (api_id) references apis (id),
    index idx_key_id (key_id),
    index idx_ip (ip),
    index idx_create_time (create_time)
) comment 'api调用记录表';

-- 题目表
create table if not exists questions
(
    id               bigint primary key auto_increment comment '题目id',
    type             tinyint  not null comment '题目类型。1选择题 2多选题 3判断题 4填空题 5解答题 6题组',
    question_format  tinyint  not null comment '题目格式。1纯文本 2图片 3LaTex(用$包裹公式) 4富文本',
    question_content text comment '题目内容',
    answer_format    tinyint  not null comment '答案格式。同题目格式',
    answer_content   json comment '答案内容。一个json字符串，具体格式参考文档',
    analysis_format  tinyint  not null comment '解析格式。同题目格式',
    analysis_content text comment '解析内容',
    origin           tinyint  not null comment '题目来源。1用户上传 2搜题无结果',
    upload_user_id   bigint comment '上传用户id',
    status           tinyint  not null comment '题目状态。1待审核 2已通过 3已驳回',
    accept_time      datetime not null comment '审核通过时间',
    reward_balance   decimal(15, 4) comment '审核通过后奖励的余额',
    create_time      datetime not null comment '创建时间',
    update_time      datetime not null comment '修改时间',
    is_deleted       bit(1)   not null default 0 comment '是否被删除',
    foreign key fk_upload_user_id (upload_user_id) references users (id),
    index idx_upload_user_id (upload_user_id),
    index idx_accept_time (accept_time),
    index idx_create_time (create_time)
) comment '题目表';

-- 工单类型
create table if not exists ticket_types
(
    id          bigint primary key auto_increment comment '工单类型id',
    name        varchar(10) not null comment '工单类型名称',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '修改时间',
    is_deleted  bit(1)      not null default 0 comment '是否被删除'
) comment '工单类型';

-- 工单表
create table if not exists tickets
(
    id             bigint primary key auto_increment comment '工单id',
    ticket_type_id bigint      not null comment '工单类型id',
    title          varchar(30) not null comment '工单标题',
    status         tinyint     not null comment '工单状态。1草稿 2已提交 3已回复 4已关闭',
    create_time    datetime    not null comment '创建时间',
    update_time    datetime    not null comment '修改时间',
    is_deleted     bit(1)      not null default 0 comment '是否被删除',
    foreign key fk_ticket_type_id (ticket_type_id) references ticket_types (id),
    index idx_ticket_type_id (ticket_type_id),
    index idx_create_time (create_time)
) comment '工单表';

-- 工单内容表
create table if not exists ticket_content
(
    id          bigint primary key auto_increment comment '工单内容id',
    ticket_id   bigint       not null comment '工单id',
    content     varchar(200) not null comment '工单内容',
    author      tinyint      not null comment '作者。1用户 2客服',
    create_time datetime     not null comment '创建时间',
    update_time datetime     not null comment '修改时间',
    is_deleted  bit(1)       not null default 0 comment '是否被删除',
    foreign key fk_ticket_id (ticket_id) references tickets (id),
    index idx_ticket_id (ticket_id),
    index idx_create_time (create_time)
) comment '工单内容表';

-- 工单附件表
create table if not exists ticket_appendices
(
    id                bigint primary key auto_increment comment '工单附件id',
    ticket_content_id bigint       not null comment '工单内容id',
    appendix_url      varchar(128) not null comment '附件url',
    create_time       datetime     not null comment '创建时间',
    update_time       datetime     not null comment '修改时间',
    is_deleted        bit(1)       not null default 0 comment '是否被删除',
    foreign key fk_ticket_content_id (ticket_content_id) references ticket_content (id),
    index idx_ticket_content_id (ticket_content_id)
) comment '工单附件表';

-- 通知表
create table if not exists notices
(
    id          bigint primary key auto_increment comment '通知id',
    title       varchar(30) not null comment '通知标题',
    content     text        not null comment '通知内容。html格式',
    is_shown    bit(1)      not null comment '是否被展示',
    create_time datetime    not null comment '创建时间',
    update_time datetime    not null comment '修改时间',
    is_deleted  bit(1)      not null default 0 comment '是否被删除',
    index idx_create_time (create_time)
) comment '通知表';

-- 通知附件表
create table if not exists notice_appendices
(
    id           bigint primary key auto_increment comment '通知附件id',
    notice_id    bigint   not null comment '通知id',
    appendix_url varchar(128) comment '附件url',
    create_time  datetime not null comment '创建时间',
    update_time  datetime not null comment '修改时间',
    is_deleted   bit(1)   not null default 0 comment '是否被删除',
    foreign key fk_notice_id (notice_id) references notices (id),
    index idx_notice_id (notice_id)
) comment '通知附件表';
