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
    customYandexMap.events.add('click', function (e) {
        var coords = e.get('coords');
        $("#latitude").val(coords[0]);
        $("#longitude").val(coords[1]);
    });

    //загрузка данных
    var mapPointApi = "http://localhost:8080/map_point"; //"mock/data.json";
    $.getJSON(mapPointApi, {
            pageNumber: "0",
            size: "5"
        })
        .done(function (data) {
            var myObjects = [];
            $.each(data.content, function (i, content) {
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
                $.ajax({
                    type: "GET",
                    url: "/map_point/" + objectId + "/link",
                    success: function (data) {
                        window.location.replace(data);
                    }
                });
            });
        });

    // Создание метки
    function createPlacemark(coords, name) {
        var placemark = new ymaps.Placemark(coords, {
            name: name
        });
        return placemark;
    }

    //отправка данных
    $("#addForm").submit(function (event) {
        event.preventDefault();

        var url = "/map_point";

        var posting = $.post(url,
            {
                shortName: $("#name").val(),
                latitude: $("#latitude").val(),
                longitude: $("#longitude").val(),
                link: $("#link").val()
            });

        posting.done(function (data) {
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



