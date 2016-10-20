function ModalsController($scope, $uibModal, $log)
 {
    var self = this
    self.animationsEnabled = true;
    self.openEdit = function(x){
        $uibModal.open({
            animation: self.animationsEnabled,
            ariaLabelledBy: 'modal-title-bottom',
            ariaDescribedBy: 'modal-body-bottom',
            templateUrl: 'editModal.html',
            controller: function(){
                modals = this
                modals.ok = function(){
                    alert("ff")
                }
            },
            controllerAs: "modals",
            size: 'sm'
        });
    };
 }