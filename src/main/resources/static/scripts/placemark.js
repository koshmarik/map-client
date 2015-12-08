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
    $.getJSON("/map_point", //"mock/data.json";,
        {
            pageNumber: "0",
            size: "5"
        })
        .done(function (data) {
            var myObjects = [];
            $.each(data.content, function (i, content) {
                myObjects.push(
                    createPlacemark(content.id, content.latitude, content.longitude, content.shortName)
                );
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
    function createPlacemark(id, latitude, longitude, name) {
       return {
           type: 'Feature',
           id: id,
           geometry: {
               type: 'Point',
               coordinates: [latitude, longitude]
           },
           name: name
       };
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
            mapObjectManager.add(
                createPlacemark(data.id, data.latitude, data.longitude, data.shortName)
            );
        });
    });


}



