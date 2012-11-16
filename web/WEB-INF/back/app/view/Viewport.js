Ext.define('app.view.Viewport', {
    extend: 'Ext.container.Viewport',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'tabpanel',
                    height: 846,
                    activeTab: 0,
                    items: [
                        {
                            xtype: 'form',
                            height: 822,
                            layout: {
                                type: 'absolute'
                            },
                            title: '图片上传',
                            items: [
                                {
                                    xtype: 'filefield',
                                    x: 30,
                                    y: 30,
                                    name: 'photo',
                                    fieldLabel: '上传图片',
                                    labelWidth: 60,
                                    msgTarget: 'side',
                                    allowBlank: false,
                                    buttonText: '选择'
                                },
                                {
                                    xtype: 'button',
                                    handler: function(button, event) {
                                        var form = this.up('form').getForm();
                                        if(form.isValid()){
                                            form.submit({
                                                url: '/lefoto/media/upload.html',
                                                waitMsg: 'Uploading your photo...',
                                                success: function(fp, o) {
                                                    Ext.Msg.alert('Success', 'Your photo "' + o.result.file + '" has been uploaded.');
                                                }
                                            });
                                        }
                                    },
                                    x: 290,
                                    y: 30,
                                    text: '上传'
                                }
                            ]
                        },
                        {
                            xtype: 'panel',
                            title: 'Tab 2'
                        },
                        {
                            xtype: 'panel',
                            title: 'Tab 3'
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});