--参展预登记
CREATE TABLE [dbo].[t_preregister_show] (
[show_id] int NOT NULL ,
[name] varchar(255) NULL ,
[company_name] varchar(500) NULL  ,
[tel] varchar(20) NULL ,
[email] varchar(255) NULL ,
[loc] varchar(255) NULL  ,
PRIMARY KEY ([show_id])
)
--主要展品
CREATE TABLE [dbo].[t_exhibit_item] (
[item_id] int NOT NULL IDENTITY(1,1) ,
[name_en] varchar(255) NULL ,
[name_zh] varchar(255) NULL  ,
[name_tw] varchar(255) NULL  ,
PRIMARY KEY ([item_id])
)
--参展预登记主要展品
CREATE TABLE [dbo].[t_show_item] (
[show_item_id] int NOT NULL IDENTITY(1,1) ,
[show_id] int NULL ,
[item_id]int NULL  ,
PRIMARY KEY ([show_item_id])
)
--参观预登记
CREATE TABLE [dbo].[t_preregister_visitor] (
[visitor_id] int NOT NULL ,
[name] varchar(255) NULL ,
[company_name] varchar(500) NULL  ,
[tel] varchar(20) NULL ,
[email] varchar(255) NULL ,
[position] varchar(255) NULL  ,--职务
PRIMARY KEY ([visitor_id])
)
--展商详情留言
CREATE TABLE [dbo].[t_comments] (
[comments_id] int NOT NULL IDENTITY(1,1) ,
[exhibitor_id] int NULL ,
[user_id] int NULL ,
[content] varchar(1000) NULL  ,
[create_on] date NULL,
PRIMARY KEY ([comments_id])
)
DROP TABLE [ExhibitorList];
DROP VIEW [ExhibitorList];
--从表t_exhibitor和 t_exhibitor_info创建ExhibitorList视图
select i.eid [ID],b.booth_number [ExhibitionNo], 
case when a.area_name is not null then a.area_name else a.area_name_en end [Country],
e.[Company],[CompanyE],e.[Company] [ShortCompanyName],
[CompanyE] [ShortCompanyNameE],e.[Company] [FirstCompany],
[CompanyE] [FirstCompanyE],[Address],i.address_en [AddressE],
i.zipcode [PostCode],i.phone [Telephone],[Fax],[Email],
[Website],i.main_product [MainProduct],i.main_product_en [MainProductE],
null [ProductType],null [ProductTypeDetail],null [ProductTypeOther],
i.logo[LogoURL],i.create_time [PostTime],'cn'[FromFlag],null [Status],
null [Seq],i.mark [Remark],0 [IsDisabled],e.create_user [CreatedBy],
i.create_time [CreatedTime],e.update_user[UpdatedBy],i.update_time [UpdateTime] 
from t_exhibitor e left join t_exhibitor_info i on e.eid = i.eid
 left join t_exhibitor_booth b on e.eid = b.eid 
 left join t_exhibitor_area a on e.country = a.id
where 1=1

--展商展位的关系表，数据来源展商后台，通过函数视图实现
create function F_ExhibitorRelation()
RETURNs  @ExhibitorRelation TABLE(
ID int not null,
ExhibitorListID int not null,
ServiceDesk varchar(20),
LocationNo varchar(100),
Th  varchar(100),
IsDisabled int ,
CreatedBy varchar(50),
createtime datetime,
UpdatedBy varchar(50),
UpdatedTime datetime
)
as 
BEGIN

WITH BOOTHTEMP AS 
(select eid, 
	booth_number = cast(
        left(LTRIM(booth_number),charindex(',',LTRIM(booth_number)+',')-1) 
      as NVARCHAR(100)),
  split = cast(
        stuff(LTRIM(booth_number)+',',1,charindex(',',LTRIM(booth_number)+','),'')
      as NVARCHAR(100)),
   create_user ,create_time ,
   update_user ,update_time 
  FROM t_exhibitor_booth
 union ALL 
select eid, 
booth_number = cast(left(split,charindex(',',split)-1) as NVARCHAR(100)),
split = cast(stuff(split,1,CHARINDEX(',', split),'') as NVARCHAR(100)),
   create_user ,create_time ,
   update_user ,update_time 
FROM 
BOOTHTEMP
where split>''
)
insert into @ExhibitorRelation
 select ROW_NUMBER() over (order by eid) as id,eid as ExhibitorListID,
  '' as ServiceDesk,booth_number as LocationNo, '2014' as Th,0 as IsDisabled,
   create_user as createBy,create_time as createTime,
   update_user as updateBy,update_time as updateTime
 from BOOTHTEMP
ORDER BY EID
option (maxrecursion 0)
return 
END

create view ExhibitorRelation
as 
select * from F_ExhibitorRelation()

--展商头像 产品图片
update t_exhibitor_info set logo = substring(logo, CHARINDEX('/', logo)+1, LEN(logo)-(CHARINDEX('/', logo)-1)); 
alter table t_product add product_name_tw varchar(800);
update t_product_image set image = substring(image, CHARINDEX('/', image)+1, LEN(image)-(CHARINDEX('/', image)-1));