INSERT INTO customer (email, parole, firstname, secondname,phonenumber)
/*customeradmin,parole 11111*/
VALUES ('alena@mail.ru',
        '$2a$11$pUOcOtnIBdvYyYRYIIPuN..7gJNR8XlUm2bYTem73SqNbBhfcyEJW',
        'Alena', 'Ordina','89217644062');
        /*customeruser,parole 11111*/
INSERT INTO customer (email, parole, firstname, secondname,phonenumber)
VALUES ('katya@mail.ru',
        '$2a$11$ChjmrLIeQzGEMBxAxA2F7.xDUz1KFN3jWC9SzTRz6NDchpVXWHw9.',
        'Ekaterina', 'Ivanova','89111111111');

INSERT INTO role(role_name) VALUES ('ROLE_USER');
INSERT INTO role(role_name) VALUES ('ROLE_ADMIN');

INSERT INTO customer_roles(customer_id, role_id) VALUES (1,2);
INSERT INTO customer_roles(customer_id, role_id) VALUES (2, 1);

INSERT INTO product (title, price, category, parameter_id, count, description, notavailable)
VALUES('Lip Lingerie','7','Lipstick',8,100,'Slip into something seductive with Lip Lingerie,' ||
 ' our luxurious liquid lipstick with a plush, Matte finish.' ||
  ' Available in the color-kissed hues you know and love—from cinnamon pink and chocolate brown,' ||
   ' to warm mahogany red and classic nude beige—plus, 12 brand-new nudes! Each sultry shade will ' ||
    'coat the curves of your lips with irresistibly creamy color.',0);

INSERT INTO product (title, price, category, parameter_id, count, description, notavailable)
VALUES('Liquid Suede','7','Lipgloss',6,100,'Wrap your lips in high-impact metallic color with Liquid Suede Metallic Matte, ' ||
 'a striking new lip inspired by our best-selling Liquid Suede Cream Lipstick.' ||
 ' Available in 12 statement-making shades, these shockingly creamy lippies deliver' ||
  ' instant dimension and incredible color payoff. In other words, get ready to slay.',0);
INSERT INTO product_parameter (brand, color, weight)
VALUES ('NYX', 'Cheekies', 5);
INSERT INTO product_parameter (brand, color, weight)
VALUES ('NYX', 'Prague', 4);
INSERT INTO role(role_name) VALUES ('ROLE_USER');
INSERT INTO role(role_name) VALUES ('ROLE_ADMIN');