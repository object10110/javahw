
drop procedure  if exists sp_new_product;
delimiter $$
use online_shop $$
create procedure sp_new_product(
    supplier_id int,
    product_name varchar(50),
    category_name varchar(50),
    net_retail_price decimal(10, 2),
    available_quantity int,
    wholesale_price decimal(10, 2),
    unit_kg_weight decimal(10, 5),
    out product_id int
)
begin
    declare id int;
    select ProductCategoryID into id from productcategories where ProductCategoryName = category_name;
    case id when null then
        begin
            insert into productcategories(ProductCategoryName) values (category_name);
            set id = last_insert_id ();
        end;
        else
            set id = id;
    end case;
    insert into products(productcategoryid, supplierid, productname, netretailprice, availablequantity, wholesaleprice, unitkgweight)
      values (id, supplier_id, product_name, net_retail_price, available_quantity, wholesale_price, unit_kg_weight);
    set product_id = last_insert_id ();
end $$
delimiter ;
select * from productcategories

