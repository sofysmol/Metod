function SpecialtyController($scope, specialty, disciplines, tables, pages, allDisciplines){
    var self = this
    $scope.index = 3
    $scope.page = pages[2]
    $scope.item = specialty
    $scope.tables = tables
    $scope.tables[$scope.index] = { name: $scope.page.slaveName,
                                    headers:$scope.page.slaveHeaders,
                                    keys:$scope.page.slaveKeys}
    $scope.tableContent = []
    $scope.addController = openAddDisBySpec(specialty)
    $scope.deleteController = openDeleteDefault("/specialties/discipline")
    $scope.editController = openEditNone
    $scope.templateAddition = 'additionModalWithChoiceDis.html'
    $scope.templateEdit = ''
    $scope.canHref = true
    $scope.allDisciplines = allDisciplines
    $scope.tableContent[$scope.index] = disciplines
    angular.forEach($scope.tableContent[$scope.index], function(value, k){
            value.params="/"+encodeURI(specialty.code)+"/"+
            encodeURI(specialty.codeKaf)+"/"+encodeURI(specialty.codeForm)
            value.codeSpec = specialty.code
            value.codeKaf = specialty.codeKaf
            value.codeForm = specialty.codeForm
    })
}
SpecialtyController.resolve = {
    specialty:function($route, Specialties){
        let params = $route.current.params
        return Specialties.getByCodeKafAndForm(params.codeKaf,
                            params.codeForm, params.codeSpec)
    },
    disciplines:function($route, Disciplines){
        let params = $route.current.params
        return Disciplines.getByCodeSpecialty(params.codeSpec,
                                               params.codeForm,
                                                params.codeKaf)
    },
    tables:function(TablesList){
            return TablesList.query();
    },
    pages:function(MainSlavePages){
            return MainSlavePages.query();
    },
    allDisciplines: function(Disciplines){
            return Disciplines.query()
    }

}