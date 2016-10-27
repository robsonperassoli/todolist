'use strict';

angular.module('todoApp', [
  'ngRoute',
  'todoApp.todo_list'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/todo-list'});
}]);
