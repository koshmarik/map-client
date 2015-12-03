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


    var mapPointApi = "http://localhost:8080/map_point";
    //"data.json"
    $.getJSON( mapPointApi, {
            pageNumber: "0",
            size: "5"
        })
        .done(function(data) {
            var myObjects = [];
            $.each( data.content, function( i, item ) {
                myObjects.push({
                    type: 'Feature',
                    id: i,
                    geometry: {
                        type: 'Point',
                        coordinates: [55.71677, 37.482338]
                    }
                });
            });
            mapObjectManager.add(myObjects);
        });
}

