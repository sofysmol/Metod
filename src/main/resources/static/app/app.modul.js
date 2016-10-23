/**
 * Created by user on 8/28/16.
 */
var app = angular.module('metodClientApp', ['ngRoute','dataServices', 'ui.bootstrap', 'ui.bootstrap.tpls', 'modalWindows']);
app.run(function($rootScope){
      $rootScope.Utils = {
         keys: Object.keys,
         update: function(srcObj, destObj){
                      for (var key in destObj) {
                        if(destObj.hasOwnProperty(key) && srcObj.hasOwnProperty(key)) {
                          destObj[key] = srcObj[key];
                        }
                      }
                  },
         range: function(n){
                return Array.from(Array(n).keys())
            }
         }

})
app.config(function ($routeProvider) {
    $routeProvider
        .when('/',
            {
                templateUrl: "app/resources/templates/app.html",
                controller: AppController,
                controllerAs:'ctrl',
                resolve:AppController.resolve
            }
        ).when('/fakultet/info/:code',{
            templateUrl: "app/resources/templates/fakultet.html",
            controller: FakultetController,
            resolve: FakultetController.resolve
        })
});
app.service("otcDynamic", function($compile)
{
    return function(templateUrl, scope)
    {
        var content = ""
        $.ajax({
            url: templateUrl,
            async: false,
            success: function (data) {
                var linkFn = $compile(data);
                content = linkFn(scope);
            }
        });
        return content
    }
});