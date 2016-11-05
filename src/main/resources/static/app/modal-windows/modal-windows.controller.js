function ModalsController($scope, $uibModal, $log,$http, $rootScope)
{
    var parent = $scope.$parent
    var self = this
    self.animationsEnabled = true;
    //let editController;
    //let addController;
    //let deleteController
    /*$scope.init = function(x)///editController)
      {
        switch(x){
            case 1:{
                editController = openEdit1($scope,$http,$rootScope)
                addController = openAdd1($scope,$http,$rootScope)
                deleteController = openDelete1($scope)
            }

        }
        //$scope.edit = editController;

      };*/

    self.openEdit = function(x){
        $rootScope.modalInstance = $uibModal.open({
            animation: self.animationsEnabled,
            ariaLabelledBy: 'modal-title-bottom',
            ariaDescribedBy: 'modal-body-bottom',
            templateUrl: parent.templateEdit,
            scope: $scope,
            controller: parent.editController($scope,$http,$rootScope)(x),
            controllerAs: "modals",
            size: 'lg'
        });

    };
    self.delete = parent.deleteController($scope,$http)
    self.openAddition= function(){
        $rootScope.modalInstance = $uibModal.open({
                    animation: self.animationsEnabled,
                    ariaLabelledBy: 'modal-title-bottom',
                    ariaDescribedBy: 'modal-body-bottom',
                    templateUrl: parent.templateAddition,
                    scope: $scope,
                    controller: parent.addController($scope,$http,$rootScope)(),
                    controllerAs: "modals",
                    size: 'lg'
                });
    }
 }