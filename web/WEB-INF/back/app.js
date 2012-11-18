Ext.Loader.setConfig({
    enabled: true,
    paths: {
        'Ext.ux': '/lefoto/back/extjs/ux'
    }
});

Ext.application({
    name: 'lefoto',
    autoCreateViewport: false,
    launch: function(){
        Ext.create('lefoto.view.Viewport');
    },
    controllers: [
        'HomeController'
    ]
});
