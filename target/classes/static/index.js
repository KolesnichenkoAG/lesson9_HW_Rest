angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                name_part: $scope.filter ? $scope.filter.name_part : null,
                min_cost: $scope.filter ? $scope.filter.min_cost : null,
                max_cost: $scope.filter ? $scope.filter.max_cost : null,
            }
        }).then(function (response) {
                $scope.ProductsList = response.data.content;

            });
    };

    $scope.loadCartProducts = function () {
        $http({
            url: contextPath + '/carts',
            method: 'GET',
        }).then(function (response) {
            $scope.ProductsCartList = response.data;
        });
    };

    $scope.deleteProductInCart = function (productId) {
        console.log('Click0')
        $http.get(contextPath + '/carts/delete/' + productId)
            .then(function (response) {
                $scope.loadCartProducts();
            });
    }

    $scope.deleteProduct = function (productId) {
        console.log('Click')
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.createProduct = function () {
        console.log('Click2');
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response) {
                $scope.loadProducts();
                $scope.newProduct = null;
            });
    }

    $scope.addProductToCart = function (productId) {
        console.log('Click3');
        $http.get(contextPath + '/carts/add/' + productId)
            .then(function (response) {
                $scope.loadCartProducts();
            });
    }

    $scope.loadProducts();
    $scope.loadCartProducts();

});