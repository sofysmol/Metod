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
        ).when('/faculties/info/:code',{
            templateUrl: "app/resources/templates/main-slave-table.page.html",
            controller: FacultyController,
            resolve: FacultyController.resolve
        }).when('/kafedras/info/:code',{
            templateUrl: "app/resources/templates/main-slave-table.page.html",
            controller: KafedraController,
            resolve: KafedraController.resolve
        }).when('/specialties/info/:codeSpec/:codeKaf/:codeForm',{
                      templateUrl: "app/resources/templates/main-slave-table.page.html",
                      controller: SpecialtyController,
                      resolve: SpecialtyController.resolve
        }).when('/disciplines/info/:codeDis/:codeSpec/:codeKaf/:codeForm',{
                                templateUrl: "app/resources/templates/main-slave-table.page.html",
                                controller: DisciplineSpecController,
                                resolve: DisciplineSpecController.resolve
        })/*.when('/disciplines/info/:codeDis'){
            templatesUrl: "app/resources/templates/main-slave-table.page.html",
            controller: DisciplineController,
            resolve: DisciplineController.resolve
        }*/


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