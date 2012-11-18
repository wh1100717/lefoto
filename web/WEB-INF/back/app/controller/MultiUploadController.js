Ext.define('lefoto.controller.MultiUploadController',{ 
    extend: 'Ext.app.Controller', 
    views: ['lefoto.view.MultiUploadView'], 
    init:function(){
        console.log('Initialized Users! This happens before the Application launch function is called');
        this.control({
            'multiuploadview > ux-multiupload-panel': {
            	render: this.fileuploadcomplete
            }
        }); 
    },

    fileuploadcomplete: function(){
        alert(123);
//        Ext.getCmp('imagePanel').store.add({id: record});
    }
});

