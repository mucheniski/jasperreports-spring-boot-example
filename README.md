# jasperreports-spring-boot-example  

Exemplos  
https://www.youtube.com/watch?v=yJoXaGeDXdM  

https://www.youtube.com/watch?v=pc4lfKm8NLY&t=5s  

Valores monetarios no Jasper Reports
Para Reais deve ser passado o Locale como parâmetro para o report ex:
~~~java
parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));
~~~
https://cafetiria.wordpress.com/2016/05/07/como-formatar-campo-como-numero-data-etc-no-jaspersoft-studio/
![](/img/exemploValorMotetarioJasperReports.png)  

Configuração de valores monetários  
ir em textField > Pattern > Selecionas Currency e deixar com ¤#,##0.00;¤-#,##0.00 com duas casas decimais  

Configuração de data  
no campo deixar new SimpleDateFormat("MM/dd/yyyy").format($F{dataPagamento})
ir em textField > Pattern > HH:mm:ss
