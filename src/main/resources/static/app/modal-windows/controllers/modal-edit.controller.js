function openEdit1($scope, $http, $rootScope){
    return function(x){
        var parent = $scope.$parent
        $scope.newItem = angular.copy(x)
         var table = parent.tables[parent.index]
         $scope.table = table
         modals = this
         modals.ok = function(){
         $http({
               method: 'PUT',
               data: $scope.newItem,
               url: encodeURI('/'+table.name)
         }).then(function successCallback(response) {
                      $rootScope.Utils.update(response.data, x)
                      $rootScope.modalInstance.close('a')
                 }, function errorCallback(response) {
                      $rootScope.modalInstance.close('a')
                      alert("Ошибка: не получилось изменить данные")
                 });
         }
         modals.cancel = function(){
            $rootScope.modalInstance.close('a')
         }
     }
}
function openEditSheduler(codeDis, codeSpec, codeForm, codeKaf){
    return function($scope, $http, $rootScope){
        return function(x){
            var parent = $scope.$parent
            $scope.newItem = angular.copy(x)
            $scope.newItem.codeDis = codeDis
             $scope.newItem.codeSpec = codeSpec
             $scope.newItem.codeForm = codeForm
             $scope.newItem.codeKaf = codeKaf
             var table = parent.tables[parent.index]
             $scope.table = table
             modals = this
             modals.ok = function(){
             $http({
                   method: 'PUT',
                   data: $.param($scope.newItem),
                   headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                   url: "/disciplines/sheduler"
             }).then(function successCallback(response) {
                          $rootScope.Utils.update($scope.newItem, x)
                          $rootScope.modalInstance.close('a')
                     }, function errorCallback(response) {
                          $rootScope.modalInstance.close('a')
                          alert("Ошибка: не получилось изменить данные")
                     });
             }
             modals.cancel = function(){
                $rootScope.modalInstance.close('a')
             }
        }
     }
}
function openEditNone($scope, $http, $rootScope){
         return function(x){}
         }