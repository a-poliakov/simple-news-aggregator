# simple-news-aggregator

Задеплоенный, запущенный проект:
http://jquiz.azurewebsites.net/newsaggregator/
(хостинг неторопливый, поэтому лучше смотреть на локальном компьютере :)

## Задание:
Нужно написать агрегатор новостей.
Пользователь подает на вход адрес новостного сайта или его RSS-ленты и правило парсинга (формат правила — на усмотрение разработчика).
1. Интерфейс — веб. Остальное на выбор разработчика.
2. Правило — некий текст, из которого модуль должен понять, как парсить тот или иной сайт. Причем это может быть и не rss.

База данных агрегатора начинает автоматически пополняться новостями с этого сайта.
У пользователя есть возможность просматривать список новостей из базы и искать их по подстроке в заголовке новости.
В качестве примера требуется подключить два любых новостных сайта на выбор.
Результат — исходный код агрегатора, а также рабочие адреса и правила парсинга, которые можно подать ему на вход.
Язык — Java. Хранилище — любая реляционная база данных. Плюсом будет использование ORM Hibernate.

## Правила парсинга:
type=auto/user
content-type=xml/html
feed-tag=... [optional]
feed-class=...
channel-tag=... [optional]
channel-className=... [optional]
item-class=...
item-tag=... [optional]
title-class=...
title-tag=...[optional]
description-class=...
description-tag=...[optional]
publishedDate-class=... [optional]
publishedDate-tag=... [optional]
link-class=... [optional]
link-tag=... [optional]

### Пример (https://news.yandex.ru/):
type=user
content-type=html
feed-tag=div
feed-class=rubric
channel-tag=a
channel-className=title
item-class=story__content
item-tag=div
title-class=story__title
title-tag=a
description-class=story__text
description-tag=div

### Пример (https://news.mail.ru/politics/):
type=user
content-type=html
feed-tag=div
feed-class=cols
channel-tag=a
channel-className=title
item-class=newsitem
item-tag=div
title-class=newsitem__title
title-tag=a
description-class=newsitem__text
description-tag=div