Ext.Loader.setConfig({
    enabled: true
});

Ext.application({
    autoCreateViewport: true,
    name: 'app',
    controllers: [
        'HomeController'
    ]
});
