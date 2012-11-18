Ext.define('lefoto.view.MultiUploadView', {
    extend: 'Ext.container.Viewport',
    requires: [
    'Ext.ux.multiupload.Panel'
    ],

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
                        xtype: 'ux-multiupload-panel',
                        title: '上传',
                        x: 50,
                        y: 60,
                        width: 600,
                        height: 300,
                        frame: true,
                        uploadConfig: {
                            uploadUrl: '/lefoto/backManage/upload.html',
                            maxFileSize: 4 * 1024 * 1024,
                            maxQueueLength: 5
                        },
                        listeners :{
                            fileuploadcomplete:function(id){
                                Ext.getCmp('imagePanel').store.add({
                                    id: id
                                });
                            }
                        }
                    },
                    {
                        xtype:'gridpanel',
                        id : "imagePanel",
                        title: '图片',
                        x: 50,
                        y: 350,
                        width: 600,
                        height: 300,
                        frame: true,
                        margin: '5 0 0',
                        store: {
                            fields: ['id']
                        },
                        columns: [
                        {
                            header: 'Id', 
                            dataIndex: 'id', 
                            width: 250
                        },
                        {
                            header: 'Image',
                            dataIndex: 'id',
                            width: 120,
                            align: 'center',
                            sortable: false,
                            renderer: function (v) {
                                return Ext.String.format('<img src="/lefoto/upload/{0}" />', v);
                            }
                        }
                        ]
                    }
                    //                    {
                    //                        xtype: 'filefield',
                    //                        x: 30,
                    //                        y: 30,
                    //                        name: 'myfiles',
                    //                        fieldLabel: '上传图片',
                    //                        labelWidth: 60,
                    //                        msgTarget: 'side',
                    //                        allowBlank: false,
                    //                        buttonText: '选择'
                    //                    },
                    //                    {
                    //                        xtype: 'button',
                    //                        handler: function(button, event) {
                    //                            var form = this.up('form').getForm();
                    //                            if(form.isValid()){
                    //                                form.submit({
                    //                                    url: '/lefoto/media/upload.html',
                    //                                    waitMsg: 'Uploading your photo...',
                    //                                    success: function(fp, o) {
                    //                                        Ext.Msg.alert('Success', 'Your photo "' + o.result.file + '" has been uploaded.');
                    //                                    }
                    //                                });
                    //                            }
                    //                        },
                    //                        x: 290,
                    //                        y: 30,
                    //                        text: '上传'
                    //                    }
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