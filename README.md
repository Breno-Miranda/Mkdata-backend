# Mkdata (Java EE Jersey, Maven, Tomcat8.5 and Mysql)

- Api REstfull 

![Mkdata](https://media-exp1.licdn.com/dms/image/C4E0BAQF3sc70KSjyOA/company-logo_200_200/0/1564798132488?e=1619049600&v=beta&t=hBt6NfjtdEB377c8eAMbuif06l0noXgi9iQ3Tdx4jeM)

  - Java EE
  - Jersey
  - Maven
  - MySql
  - Tomcat8.5

### Installation

Só precisa de alguns passo:

```sh
$ gir clone url_do_projeto

```

# Run eclipse the "tomcat 8.5 server"

#### MYSQL (SCRIPT)


```sh
create database jetecommerce;

use simplescrud;


create table tbusers(
	  id int not null auto_increment primary key,
    name varchar(150) not null,
    cpfcnpj varchar(50) not null,
    type varchar(15) not null,
    rgie  varchar(50) not null,
    isactive int not null default 0,
	  created_at timestamp not null
)ENGINE=InnoDB AUTO_INCREMENT=1;

create table tbphoneusers(
	  id int not null auto_increment primary key,
    userId int not null,
    areacode int(2) not null,
    phone varchar(150) not null,
	  ismain int not null default 0,
    created_at timestamp not null,
	constraint fk_Users foreign key (userId) references tbusers(id)
)ENGINE=InnoDB AUTO_INCREMENT=1;

```


### Plugins

Os Plugins utilizados foram:

| Plugin |
| ------ | 
| java ee |
| maven |
| jersey |
| tomcat 8.5 |
| mysql | 

