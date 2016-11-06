function openAdd1($scope, $http, $rootScope){
    return function(){
           var parent = $scope.$parent
           var table = parent.tables[parent.index]
           $scope.table = table
           $scope.newItem = {}
           modals = this
           modals.ok = function(){
                 $http({
                       method: 'POST',
                       data: $scope.newItem,
                       url: encodeURI('/'+table.name)
                 }).then(function successCallback(response) {
                    parent.tableContent[parent.index].push($scope.newItem)
                    $rootScope.modalInstance.close('a')
                 }, function errorCallback(response) {
                    alert("Ошибка: не получилось добавить данные")
                    $rootScope.modalInstance.close('a')
                 });
           }
           modals.cancel = function(){
                $rootScope.modalInstance.close('a')
           }
           }
}
function openAddDisBySpec(specialty){
        return function($scope, $http, $rootScope){
                 return function(){
                       var parent = $scope.$parent
                       $scope.newItem = {
                                codeSpec: specialty.code,
                                codeKaf: specialty.codeKaf,
                                codeForm: specialty.codeForm,
                                semester: 1,
                                lecture: 0,
                                prak: 0,
                                lab: 0,
                                kurs: 0,
                                report: "зачет"
                            }
                       modals = this
                       $scope.disciplines = {
                                   options: parent.allDisciplines,
                                   selected: parent.allDisciplines[0].code
                                 };
                       modals.ok = function(){
                           $scope.newItem.code = $scope.disciplines.selected
                           var i=0
                           while($scope.newItem.code != $scope.disciplines.options[i].code
                                && i<$scope.disciplines.options.length) i++
                           $scope.newItem.name = $scope.disciplines.options[i].name
                           postAndAddSomething($http, $rootScope,"/specialties/discipline",
                                      $scope.newItem, parent.tableContent, parent.index)
                       }
                        modals.cancel = function(){
                            $rootScope.modalInstance.close('a')
                        }
                 }
        }
}
function openAddSpecByKaf(codeKaf){
    return function($scope, $http, $rootScope){
             return function(){
                   var parent = $scope.$parent
                   $scope.newItem = {
                        codeKaf: codeKaf
                   }
                   modals = this
                   $scope.specialties = {
                           options: parent.allSpecialties,
                           selected: parent.allSpecialties[0].code
                         };
                   modals.ok = function(){
                       $scope.newItem.code = $scope.specialties.selected
                       $scope.newItem.codeForm = $scope.forms.selected
                       var i = 0
                       while($scope.specialties.options[i].code != $scope.specialties.selected) i++
                       $scope.newItem.name = $scope.specialties.options[i].name
                       postAndAddSomething($http, $rootScope,"/kafedras/specialty",
                                        $scope.newItem, parent.tableContent, parent.index)
                   }
                    modals.cancel = function(){
                        $rootScope.modalInstance.close('a')
                    }
             }
        }
}
function openAddKafByFak(codeFak){
    return function($scope, $http, $rootScope){
         return function(){
               var parent = $scope.$parent
               $scope.newItem = {
                    codeFak:codeFak
               }
               var table = parent.tables[parent.index]
               $scope.table = table
               modals = this
               modals.ok = function(){
                   postAndAddSomethingJson($http, $rootScope,'/'+parent.tables[parent.index].name,
                                    $scope.newItem, parent.tableContent, parent.index)
               }
                modals.cancel = function(){
                    $rootScope.modalInstance.close('a')
                }
         }
    }
}
function openAddShedulerByDis(codeDis, specialty){
    return function($scope, $http, $rootScope){
         return function(){
               var parent = $scope.$parent
               var table = parent.tables[parent.index]
               $scope.table = table
               $scope.newItem = {
                    code: codeDis,
                    codeSpec: specialty.code,
                    codeKaf: specialty.codeKaf,
                    codeForm: specialty.codeForm,
                    semester: 1,
                    lecture: 0,
                    prak: 0,
                    lab: 0,
                    kurs: 0,
                    report: "зачет"
               }
               modals = this
               modals.ok = function(){
                postAndAddSomething($http, $rootScope,"/specialties/discipline",
                 $scope.newItem, parent.tableContent, parent.index)
               }
                modals.cancel = function(){
                    $rootScope.modalInstance.close('a')
                }
         }
    }
}
function postAndAddSomething($http, $rootScope,url, something, table, index){
    $http({
          method: 'POST',
          data: $.param(something),
          url: url,
          headers: {'Content-Type': 'application/x-www-form-urlencoded'},
    }).then(function successCallback(response) {
          table[index].push(something)
          $rootScope.modalInstance.close('a')
    }, function errorCallback(response) {
          alert("Ошибка: не получилось добавить данные")
          $rootScope.modalInstance.close('a')
    });
}
function postAndAddSomethingJson($http, $rootScope,url, something, table, index){
    $http({
          method: 'POST',
          data: something,//$.param(something),
          url: url,

    }).then(function successCallback(response) {
          table[index].push(something)
          $rootScope.modalInstance.close('a')
    }, function errorCallback(response) {
          alert("Ошибка: не получилось добавить данные")
          $rootScope.modalInstance.close('a')
    });
}