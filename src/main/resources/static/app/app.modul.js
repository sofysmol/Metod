/**
 * Created by user on 8/28/16.
 */
var app = angular.module('metodClientApp', ['ngRoute','dataServices', 'ui.bootstrap',
                        'ui.bootstrap.tpls', 'modalWindows']);
app.run(function($rootScope, TablesList, TabsList){
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
            },
            encodeURI: encodeURIComponent
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
        ).when('/fakultets/info/:code',{
            templateUrl: "app/resources/templates/fakultet.html",
            controller: FakultetController,
            resolve: FakultetController.resolve
        }).when('/kafedras/info/:code',{
            templateUrl: "app/resources/templates/kafedra.html",
            controller: KafedraController,
            resolve: KaferaController.resolve
        })
});
app.filter('escape', function() {
  return window.encodeURIComponent;
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