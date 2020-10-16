写了个字符串分割函数,主要用来分割组织架构.
比如字符串为 '宇宙-地球-天朝-魔都-胖哥'
输出第三级 (天朝):
<pre class="lang:tsql decode:true">SELECT   dbo.SPLIT('宇宙-地球-天朝-魔都-胖哥', 3)</pre>
&nbsp;

超过字符串本身的**层级**有两个选项.
一种是显示最后一个层级 (默认)
SELECT   dbo.SPLIT('宇宙-地球-天朝-魔都-胖哥', 5)
SELECT   dbo.SPLIT('宇宙-地球-天朝-魔都-胖哥', 6)

另外一个则是显示为空 (取消我代码中的注释即可)
SELECT   dbo.SPLIT('宇宙-地球-天朝-魔都-胖哥', 5)
SELECT   dbo.SPLIT('宇宙-地球-天朝-魔都-胖哥', 6)


<pre class="lang:tsql decode:true ">ALTER FUNCTION [dbo].[SPLIT]
    (
      @products NVARCHAR(200) ,
      @level INT
    )
RETURNS NVARCHAR(100)
AS
    BEGIN	
        DECLARE @individual NVARCHAR(200) = NULL
        DECLARE @count INT = 1
        DECLARE @isZero INT = 0
        WHILE LEN(@products) &gt; 0
            AND @count &lt;= @level
            BEGIN
                IF CHARINDEX('-', @products) &gt; 0
                    SET @individual = SUBSTRING(@products, 0,
                                                CHARINDEX('-', @products))
                ELSE
                    IF @isZero = 0
                        BEGIN
                            SET @individual = @products
                            SET @isZero = 1
                        END
                    --ELSE
					               --     IF @isZero = 1
                    --        BEGIN 
                    --            SET @individual = ''
                    --        END
                SET @count = @count + 1
                SET @products = SUBSTRING(@products,
                                          CHARINDEX('-', @products) + 1,
                                          LEN(@products))
            END
        RETURN @individual
    END</pre>
&nbsp;