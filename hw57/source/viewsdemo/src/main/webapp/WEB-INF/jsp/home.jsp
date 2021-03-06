<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="//cdn.muicss.com/mui-0.10.0/css/mui.min.css" rel="stylesheet" type="text/css"/>
    <script src="//cdn.muicss.com/mui-0.10.0/js/mui.min.js"></script>
    <style>
        /*
        * Body CSS
        */
        html,
        body {
            height: 100%;
        }

        html,
        body,
        input,
        textarea,
        button {
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
            text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.004);
        }


        /**
         * Header CSS
         */
        header {
            position: fixed;
            top: 0;
            right: 0;
            left: 0;
            z-index: 2;
        }

        header ul.mui-list--inline {
            margin-bottom: 0;
        }

        header a {
            color: white;
        }

        header table {
            width: 100%;
        }


        /**
         * Content CSS
         */
        #content-wrapper {
            min-height: 100%;

            /* sticky footer */
            box-sizing: border-box;
            padding-bottom: 100px;
            margin: 100px 100px -100px;
        }


        /**
         * Footer CSS
         */
        footer {
            box-sizing: border-box;
            height: 100px;
            background-color: #eee;
            border-top: 1px solid #e0e0e0;
            padding-top: 35px;
        }
    </style>
</head>
<body>
<header class="mui-appbar mui--z1">
    <!-- Appbar HTML goes here -->
    <div class="mui-container">
        <table>
            <tr class="mui--appbar-height">
                <td class="mui--text-title">Online Shop</td>
                <td class="mui--text-right">
                    <ul class="mui-list--inline mui--text-body2">
                        <li><a href="<%=request.getContextPath()%>">Create product</a></li>
                        <li><a href="<%=request.getContextPath()%>/list">List product</a></li>
                    </ul>
                </td>
            </tr>
        </table>
    </div>
</header>
<div id="content-wrapper" class="mui--text-center">
    <!-- Content HTML goes here -->
    <form class="mui-form" method="post">
        <legend>New product</legend>
        <div class="mui-textfield">
            <select name="suplier_id">
                <option value="1">Acme Systems</option>
                <option value="2">Amalgamated Software</option>
                <option value="3">AWT</option>
                <option value="4">Consolidated Products</option>
                <option value="5">CHOAM</option>
                <option value="6">Sliderule Computers</option>
            </select>
        </div>
        <div class="mui-textfield">
            <input type="text" placeholder="Product name" name="product_name">
        </div>
        <div class="mui-textfield">
            <input type="text" placeholder="Category name" name="category_name"
                   list="categories">
            <datalist id="categories">
                <option>condiments</option>
                <option>food</option>
                <option>airships</option>
                <option>software</option>
                <option>books</option>
                <option>drink</option>
                <option>horse-drawn carriages</option>
            </datalist>
        </div>
        <div class="mui-textfield">
            <input type="number" step="0.01" min="0" placeholder="Net retail price" name="net_retail_price">
        </div>
        <div class="mui-textfield">
            <input type="number" min="0" placeholder="Available quantity" name="available_quantity">
        </div>
        <div class="mui-textfield">
            <input type="number" step="0.01" min="0" placeholder="Wholesale price" name="wholesale_price">
        </div>
        <div class="mui-textfield">
            <input type="number" step="0.01" min="0" placeholder="Wholesale price" name="unit_kg_weight">
        </div>
<%--        <div class="mui-textfield">--%>
<%--            <textarea placeholder="Notes" name="notes"></textarea>--%>
<%--        </div>--%>
        <button type="submit" class="mui-btn mui-btn--raised">Create</button>
    </form>

</div>
<footer>
    <div class="mui-container mui--text-center">
        Made with ♥ by <a href="https://www.muicss.com">MUICSS</a>
    </div>
</footer>

</body>
</html>
