function openEdit1(){
    $scope.item = angular.copy(x)
     var table = parent.tables[parent.index]
     $scope.table = table
     modals = this
     modals.ok = function(){
     $http({
           method: 'PUT',
           data: $scope.item,
           url: encodeURI('/'+table.name+'/'+$scope.item.code)
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