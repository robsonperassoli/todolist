'use strict';
angular.module('todoApp.todo_list', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/todo-list', {
    templateUrl: 'todo_list/list.html',
    controller: 'TodoListCtrl'
  })
  .when('/todo-list/new', {
    templateUrl: 'todo_list/form.html',
    controller: 'TodoListFormCtrl'
  })
  .when('/todo-list/:id', {
    templateUrl: 'todo_list/form.html',
    controller: 'TodoListFormCtrl'
  });
}])

.factory('TodoListService', ['$http', function ($http){
	return {
		list: function (){
			return $http.get('/todo-lists')
						.then(function (result){
							return result.data;
						});
		},
		load: function (id){
			return $http.get('/todo-lists/' + id)
						.then(function (result){
							return result.data;
						});
		},
		update: function (todoList){
			return $http.put('/todo-lists/' + todoList.id, todoList)
						.then(function (result){
							return result.data;
						});
		},
		create: function (todoList){
			return $http.post('/todo-lists', todoList)
						.then(function (result) {
							var location = result.headers('Location');

							return $http.get(location);
					    })
					    .then(function (result) {
					    	return result.data;
					    });
		},
		delete: function (id){
			return $http.delete('/todo-lists/' + id)
						.then(function (result){
							return result.data;
						});
		}
	}
}])

.factory('TaskService', ['$http', function($http){
	return {
		list: function(todoListId) {
			return $http.get('/todo-lists/' + todoListId + '/tasks')
					.then(function(result){
						return result.data;
					});
		},
		create: function(todoListId, newTask) {
			return $http.post('/todo-lists/' + todoListId + '/tasks', newTask)
						.then(function (result) {
							var location = result.headers('Location');

							return $http.get(location);
					    })
					    .then(function (result) {
					    	return result.data;
					    });
		},
		update: function (todoListId, task) {
			return $http.put('/todo-lists/' + todoListId + '/tasks/' + task.id, task)
			.then(function (result){
				return result.data;
			});
		},
		delete: function (todoListId, taskId){
			return $http.delete('/todo-lists/' + todoListId + '/tasks/' + taskId)
		}
	}
}])

.controller('TodoListCtrl', ['$scope', '$location', 'TodoListService',
	function($scope, $location, TodoListService) {

	$scope.list = [];

	(function(){
		TodoListService.list().then(function(result){
			$scope.list = result;
		});
	})();

	$scope.delete = function(id){
		TodoListService.delete(id).then(function(){
			$scope.list = $scope.list.filter(function(e){
				return e.id !== id;
			});
		});
	};
	
}])

.controller('TodoListFormCtrl', ['$scope', '$routeParams', '$location', 'TodoListService', 'TaskService', 
	function($scope, $routeParams, $location, TodoListService, TaskService) {

	(function(){
		if($routeParams.id === undefined) {
			$scope.todoList = {id: null, name: ""};
		} else {
			TodoListService.load($routeParams.id).then(function(todoList){
				$scope.todoList = todoList;

				TaskService.list($scope.todoList.id).then(function(tasks){
					$scope.tasks = tasks;
				})
			});
		}
	})();

	var newTask = function(){
		return {
			description: "", 
			done: false
		}
	};

	var descriptionEmptyOnTask = function (task){
		return task.description === undefined || task.description === null || task.description === '';
	};

	$scope.editingTask = newTask();

	$scope.create = function(){
		TodoListService.create($scope.todoList).then(function(data){
			$location.path('/todo-list/' + data.id);
		});
	};

	$scope.update = function(){
		TodoListService.update($scope.todoList);
	};
	
	$scope.editTask = function (taskToEdit){
		$scope.editingTask = taskToEdit;
	};

	$scope.createTask = function (){
		if(!descriptionEmptyOnTask($scope.editingTask)) {
			TaskService.create($scope.todoList.id, $scope.editingTask).then(function(taskSaved){
				$scope.tasks.push(taskSaved);
				$scope.editingTask = newTask();
			})
		}
	};

	$scope.updateTask = function (){
		if(!descriptionEmptyOnTask($scope.editingTask)) {
			TaskService.update($scope.todoList.id, $scope.editingTask).then(function(){
				$scope.editingTask = newTask();
			})
		}
	};

	$scope.updateThisTask = function (task){
		TaskService.update($scope.todoList.id, task)
	};

	$scope.deleteTask = function (taskId){
		TaskService.delete($scope.todoList.id, taskId).then(function(){
			$scope.tasks = $scope.tasks.filter(function(e){
				return e.id !== taskId;
			});
		});
	};

	$scope.isANewTodoList = function(){
		return $scope.todoList == undefined || $scope.todoList.id === null || $scope.todoList.id === undefined;
	}

}]);