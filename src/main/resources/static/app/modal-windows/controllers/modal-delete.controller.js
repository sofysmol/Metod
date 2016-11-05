function openDelete1($scope, $http){
    return function(x){
          var parent = $scope.$parent
          //$http.delete("/"+parent.tables[parent.index].name+"/"+x.code).then(function successCallback(){
          $http.delete("/"+parent.tables[parent.index].name, {params:{code:x.code}}).then(function successCallback(){
          var index = parent.tableContent[parent.index].indexOf(x)
          parent.tableContent[parent.index].splice(index, 1)
          }, function errorCallback(){
                  alert("Ошибка удаления")
          })
     }
}
function openDeleteDefault(url){
    return function($scope, $http){
           return function(x){
                 var parent = $scope.$parent
                 $http.delete(url,{params:x}).then(function successCallback(){
                 var index = parent.tableContent[parent.index].indexOf(x)
                 parent.tableContent[parent.index].splice(index, 1)
           }, function errorCallback(){
                 alert("Ошибка удаления")
              })
           }
    }
}
function openDeleteSpecByKaf($scope, $http){
    return function(x){
              $http.delete('/kafedras/specialty',{params:$.param(x)}).then(function successCallback(){
              var index = parent.tableContent[parent.index].indexOf(x)
              parent.tableContent[parent.index].splice(index, 1)
              }, function errorCallback(){
                      alert("Ошибка удаления")
              })
         }
}
function openDeleteDisBySpec($scope, $http){
         return function(x){
                alert(x)
               $http.delete('/specialties/discipline',{params:x}).then(function successCallback(){
               var index = parent.tableContent[parent.index].indexOf(x)
               parent.tableContent[parent.index].splice(index, 1)
         }, function errorCallback(){
              alert("Ошибка удаления")
            })
         }
}