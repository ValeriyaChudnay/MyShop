ALTER DATABASE shop CHARACTER SET utf8 COLLATE utf8_general_ci;
create table user(
  `email` varchar(100)  PRIMARY KEY ,
  `password` varchar(100) NOT NULL ,
  `firstName` varchar(100)  NOT NULL,
  `secondName` varchar(100)   NOT NULL,
  `imgPath` varchar(300) ,
  `isEmailSend` boolean DEFAULT FALSE
)CHARACTER SET utf8 ;
-- auto-generated definition
create table product
(
    id          int auto_increment
        primary key,
    name        varchar(100)  not null,
    description varchar(500)  null,
    image1      varchar(300)  not null,
    image2      varchar(300)  not null,
    idCategory  int           not null,
    price       double(10, 2) null,
    idBrand     int           not null,
    constraint product_brand__fk
        foreign key (idBrand) references brand (id),
    constraint product_category__fk
        foreign key (idCategory) references category (id)
);
-- auto-generated definition
create table category
(
    id   int          not null
        primary key,
    name varchar(300) not null
);
-- auto-generated definition
create table brand
(
    id    int          not null
        primary key,
    title varchar(100) null
);
create table temporarycart
(
    UserMail  varchar(200) not null,
    ProductId int          not null,
    Amount    int          null,
    constraint temporarycart_pk
        unique (UserMail, ProductId),
    constraint temporarycart_pk_2
        unique (ProductId),
    constraint temporaryCart_product_id_fk
        foreign key (ProductId) references product (id),
    constraint temporaryCart_user__fk
        foreign key (UserMail) references user (email)
);

alter table temporarycart
    add primary key (UserMail, ProductId);

    -- auto-generated definition
    create table ordersproduct
    (
        orderId      int  not null,
        productId    int not null,
        productPrice int  not null,
        productCount int  not null,
        constraint ordersproduct_pk
            unique (orderId, productId)
    );
    -- auto-generated definition
    create table orders
    (
        id           int auto_increment
            primary key,
        orderDate    datetime                                not null,
        userEmail    varchar(100)                            not null,
        orderMethod  varchar(50)  default 'mail'             not null,
        orderComment varchar(255) default 'Ожидает проверки' not null,
        orderStatus  int          default 0                  not null,
        address      varchar(300)                            not null,
        constraint orders_user_email_fk
            foreign key (userEmail) references user (email)
    );

