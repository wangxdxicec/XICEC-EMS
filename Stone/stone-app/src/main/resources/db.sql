CREATE TABLE t_wsc (
	wsc_id INT PRIMARY KEY,
	wsc_date DATE,
	speaker_id INT,
	intrduction TEXT
);

CREATE TABLE t_wsc_topic (
	topic_id INT PRIMARY KEY,
	wsc_id INT,
	name VARCHAR (500)
);

CREATE TABLE t_wsc_speaker (
	speaker_id INT PRIMARY KEY,
	name VARCHAR (500),
	title VARCHAR (500),
	introduction TEXT
);

CREATE TABLE t_user_remark (
	remark_id int identity(1,1) PRIMARY KEY,
	item_id VARCHAR (500),
	user_id int,
	type int,
	remark TEXT
);

CREATE VIEW ExhibitorProduct AS SELECT
	i.eid,
	t.id product_id,
	t.product_name,
	t.product_name_en,
	t.product_name_tw
FROM
	t_exhibitor_info i,
	t_product t
WHERE
	i.einfoid = t.einfo_id
	
	
	
SELECT
	i.eid [ID],
	b.booth_number [ExhibitionNo],
	isnull(a.area_name,'') Country,
	isnull(a.area_name_en,'') CountryE,
	isnull(a.area_name_t,'') CountryT,
	isnull(g.group_name,''),
	isnull(g.group_name_en,''),
	isnull(g.group_name_tw,''),
	isnull(i.company,'') company,
	isnull(i.company_en,'') CompanyE,
	isnull(i.company_t,'') CompanyT,
	isnull(i.company,'') [ShortCompanyName],
	isnull(i.company_en,'') [ShortCompanyNameE],
	isnull(i.company_t,'') [ShortCompanyNameT],
	isnull(i.company,'') [FirstCompany],
	isnull(i.company_en,'') [FirstCompanyE],
	isnull(i.address,'') Address,
	isnull(i.address_en,'') [AddressE],
	isnull(i.address_t,'') [AddressT],
	isnull(i.zipcode,'') [PostCode],
	isnull(i.phone,'') [Telephone],
	isnull(i.fax,'') fax,
	isnull(i.email,'') email,
	isnull(i.website,'') website,
	i.main_product [MainProduct],
	i.main_product_en [MainProductE],
	NULL [ProductType],
	NULL [ProductTypeDetail],
	NULL [ProductTypeOther],
	i.logo [LogoURL],
	i.create_time [PostTime],
	'cn' [FromFlag],
	NULL [Status],
	NULL [Seq],
	i.mark [Remark],
	0 [IsDisabled],
	e.create_user [CreatedBy],
	i.create_time [CreatedTime],
	e.update_user [UpdatedBy],
	i.update_time [UpdateTime]
FROM
	t_exhibitor e
LEFT JOIN t_exhibitor_info i ON e.eid = i.eid
LEFT JOIN t_exhibitor_booth b ON e.eid = b.eid
LEFT JOIN t_exhibitor_area a ON e.country = a.id
LEFT JOIN t_exhibitor_group g ON g.id = e.[group]
WHERE
	1 = 1


