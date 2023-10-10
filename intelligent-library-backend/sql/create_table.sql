-- 创建库
create database if not exists intelligent_library;

-- 切换库
use intelligent_library;

-- 用户表
create table user
(
    id            bigint                                  not null comment 'id'
        primary key,
    user_account  varchar(256)                            not null comment '账号',
    user_password varchar(512)                            not null comment '密码',
    user_name     varchar(256)                            null comment '用户昵称',
    user_avatar   varchar(1024)                           null comment '用户头像',
    user_phone    varchar(256)                            null comment '用户电话',
    user_email    varchar(512)                            not null comment '用户电子邮箱',
    user_address  text                                    null comment '用户地址',
    user_role     tinyint       default 1                 not null comment '用户角色：0:admin/1:vip1/2:vip2/3:vip3',
    balance       decimal(6, 2) default 0.00              not null comment '创建时间',
    create_time   datetime      default CURRENT_TIMESTAMP not null,
    update_time   datetime      default CURRENT_TIMESTAMP not null comment '更新时间',
    is_delete     tinyint       default 0                 not null comment '是否删除',
    constraint user_id_uindex
        unique (id)
);



create unique index user_id_uindex
    on user (id);


-- 书籍信息
create table book
(
    id                   bigint auto_increment
        primary key,
    book_isbn            varchar(512)                       not null comment '书籍ISBN编号',
    book_name            varchar(512)                       not null comment '书籍作者',
    book_avatar          varchar(1024)                      null comment '书籍图片',
    book_author          varchar(256)                       not null comment '书籍作者',
    book_publisher       varchar(256)                       null comment '书籍出版社',
    book_price           decimal(6, 2)                      not null comment '书籍价格',
    borrow_price_per_day decimal(4, 2)                      not null comment '书籍借阅价格(按天结算)',
    book_stock           int                                not null comment '书籍库存',
    book_tags            varchar(1024)                      null comment '书籍标签',
    buy_number           int      default 0                 null comment '购买总数量',
    borrow_number        int      default 0                 null comment '借阅总数量',
    book_description     varchar(1024)                      null comment '书籍描述',
    book_status          tinyint  default 0                 not null comment '书籍状态：0未上架/1已上架/2已下架',
    create_time          datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time          datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    is_delete            tinyint  default 0                 not null comment '逻辑删除',
    constraint book_id_uindex
        unique (id)
);

-- 书籍借阅记录
create table book_borrow
(
    id            bigint                             not null
        primary key,
    user_id       bigint                             not null comment '用户ID',
    book_id       int                                not null comment '书籍ID',
    borrow_number int                                not null comment '借阅书籍数量',
    total_price   decimal(4, 2)                      not null comment '借阅总价格（原价）',
    vip_price     decimal(4, 2)                      not null comment '会员价格（优惠后）',
    borrow_day    int                                not null comment '借阅天数',
    return_data   datetime                           not null,
    borrow_data   datetime default CURRENT_TIMESTAMP not null comment '借阅时间',
    create_time   datetime default CURRENT_TIMESTAMP not null,
    update_time   datetime default CURRENT_TIMESTAMP not null,
    is_delete     tinyint  default 0                 not null,
    constraint book_borrow_id_uindex
        unique (id),
    constraint book_borrow_user_id_book_id_id_uindex
        unique (user_id, book_id, id)
);

-- 书籍订单
create table book_order
(
    id           bigint                             not null
        primary key,
    user_id      bigint                             not null comment '购买用户',
    book_id      bigint                             not null comment '购买书籍',
    buy_number   int                                not null comment '购买数量',
    user_address text                               not null comment '用户地址',
    total_price  decimal(6, 2)                      not null comment '总价格（原价）',
    vip_price    decimal(6, 2)                      not null comment '会员价格（优惠价格）',
    create_time  datetime default CURRENT_TIMESTAMP not null,
    update_time  datetime default CURRENT_TIMESTAMP not null,
    is_delete    tinyint  default 0                 not null,
    constraint book_order_id_uindex
        unique (id),
    constraint book_order_id_user_id_book_id_uindex
        unique (id, user_id, book_id)
);