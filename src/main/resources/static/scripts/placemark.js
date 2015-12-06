ymaps.ready(init);

function init() {
    var customYandexMap = new ymaps.Map('map', {
            center: [55.76, 37.64],
            zoom: 10
        }, {
            searchControlProvider: 'yandex#search'
        });
    var mapObjectManager = new ymaps.ObjectManager({clusterize: true});

    customYandexMap.geoObjects.add(mapObjectManager);

    // Слушаем клик на карте
    var myPlacemark;
    customYandexMap.events.add('click', function (e) {
        var coords = e.get('coords');
        // Если метка уже создана – просто передвигаем ее
        if (myPlacemark) {
            myPlacemark.geometry.setCoordinates(coords);
        }
        // Если нет – создаем.
        else {
            /*myPlacemark = createPlacemark(coords);
            myMap.geoObjects.add(myPlacemark);
            // Слушаем событие окончания перетаскивания на метке.
            myPlacemark.events.add('dragend', function () {
                getAddress(myPlacemark.geometry.getCoordinates());
            });*/
        }
    });

    //загрузка данных
    var mapPointApi = "http://localhost:8080/map_point";
    //"data.json"
    $.getJSON( mapPointApi, {
            pageNumber: "0",
            size: "5"
        })
        .done(function(data) {
            var myObjects = [];
            $.each( data.content, function( i, content ) {
                myObjects.push({
                    type: 'Feature',
                    id: content.id,
                    geometry: {
                        type: 'Point',
                        coordinates: [content.latitude, content.longitude]
                    },
                    name: content.shortName,
                    link: content.link
                });
            });
            mapObjectManager.add(myObjects);

            mapObjectManager.objects.events.add('click', function (e) {
                var objectId = e.get('objectId');
                //mapObjectManager.objects.balloon.open(objectId);
                $.ajax({
                    type: "GET",
                    url: "/map_point/"+objectId+"/link",
                    success: function (data) {
                        window.location.replace(data);
                    }
                });
            });
        });

    // Создание метки
    function createPlacemark(coords, name, link) {
        var placemark = new ymaps.Placemark(coords, {
            name: name
        });
        return placemark;
    }

    //отправка данных
    $("#addForm").submit(function(event) {
        event.preventDefault();

        var url = "/map_point";

        var $form = $( this ),
            name = $form.find( "input[name='name']" ).val(),
            latitude = $form.find( "input[name='latitude']" ).val(),
            longitude = $form.find( "input[name='longitude']" ).val();

        var posting = $.post( url, { shortName: name, latitude: latitude, longitude: longitude } );

        posting.done(function( data ) {
            mapObjectManager.add({
                type: 'Feature',
                id: data.id,
                geometry: {
                    type: 'Point',
                    coordinates: [data.latitude, data.longitude]
                },
                name: data.shortName
            });
        });
    });



}



