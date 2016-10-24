function ModalsController($scope, $uibModal, $log,$http, $rootScope)
 {
    var parent = $scope.$parent
    var self = this
    self.animationsEnabled = true;
    self.openEdit = function(x){
        $rootScope.modalInstance = $uibModal.open({
            animation: self.animationsEnabled,
            ariaLabelledBy: 'modal-title-bottom',
            ariaDescribedBy: 'modal-body-bottom',
            templateUrl: 'editModal.html',
            scope: $scope,
            controller: function(){$scope.item = angular.copy(x)
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
                            }},
            controllerAs: "modals",
            size: 'sm'
        });

    };
    self.delete = function(x){
        var parent = $scope.$parent
        $http.delete("/"+parent.tables[parent.index].name+"/"+x.code).then(function successCallback(){
           var index = parent.tableContent[parent.index].indexOf(x)
           parent.tableContent[parent.index].splice(index, 1)
        }, function errorCallback(){
            alert("Ошибка удаления")
        })
    }
    self.openAddition= function(){
        $rootScope.modalInstance = $uibModal.open({
                    animation: self.animationsEnabled,
                    ariaLabelledBy: 'modal-title-bottom',
                    ariaDescribedBy: 'modal-body-bottom',
                    templateUrl: 'additionModal.html',
                    scope: $scope,
                    controller: function(){
                                    var table = parent.tables[parent.index]
                                    $scope.table = table
                                    $scope.item = {}
                                    modals = this
                                    modals.ok = function(){
                                             $http({
                                                    method: 'POST',
                                                    data: $scope.item,
                                                    url: encodeURI('/'+table.name)
                                             }).then(function successCallback(response) {
                                                 parent.tableContent[parent.index].push($scope.item)
                                                 $rootScope.modalInstance.close('a')
                                             }, function errorCallback(response) {
                                                  alert("Ошибка: не получилось добавить данные")
                                                  $rootScope.modalInstance.close('a')
                                             });
                                        }
                                     modals.cancel = function(){
                                                 $rootScope.modalInstance.close('a')
                                        }
                                     },
                    controllerAs: "modals",
                    size: 'sm'
                });
    }
 }