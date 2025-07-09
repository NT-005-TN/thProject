```
com.example.carparts
├── adapter
│   ├── CarAdapter.kt
│   └── PartAdapter.kt
│
├── contract
│   ├── MainContract.kt
│   ├── AddCarContract.kt
│   ├── EditCarContract.kt
│   ├── CarDetailsContract.kt
│   ├── AddPartContract.kt
│   └── EditPartContract.kt
│
├── data
│   ├── db
│   │   ├── AppDatabase.kt
│   │   ├── CarDao.kt
│   │   └── PartDao.kt
│   │
│   └── model
│       ├── Car.kt
│       └── Part.kt
│
├── presenter
│   ├── MainPresenter.kt
│   ├── AddCarPresenter.kt
│   ├── EditCarPresenter.kt
│   ├── CarDetailsPresenter.kt
│   ├── AddPartPresenter.kt
│   └── EditPartPresenter.kt
│
├── receiver
│   └── ReminderReceiver.kt
│
├── service
│   └── ReminderService.kt
│
├── util
│   ├── Constants.kt
│   └── NotificationHelper.kt
│
└── view
    ├── MainActivity.kt
    ├── AddCarActivity.kt
    ├── EditCarActivity.kt
    ├── CarDetailsActivity.kt
    ├── AddPartActivity.kt
    └── EditPartActivity.kt


res/layout/
├── activity_main.xml
├── activity_add_car.xml
├── activity_edit_car.xml
├── activity_car_details.xml
├── activity_add_part.xml
├── activity_edit_part.xml
├── item_car.xml
└── item_part.xml



res/values/
├── strings.xml
├── colors.xml
└── themes.xml

