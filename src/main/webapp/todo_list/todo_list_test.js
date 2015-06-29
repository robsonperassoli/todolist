'use strict';

describe('todoApp.todo_list module', function() {

  beforeEach(module('todoApp.todo_list'));

  describe('todo_list controller', function(){

    it('should ....', inject(function($controller) {
      //spec body
      var todoListCtrl = $controller('TodoListCtrl');
      expect(todoListCtrl).toBeDefined();
    }));

  });
});