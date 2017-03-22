# simple-news-aggregator

Задеплоенный, запущенный проект:
http://jquiz.azurewebsites.net/newsaggregator/ <br>
(хостинг неторопливый, поэтому лучше смотреть на локальном компьютере :)

## Задание:
Нужно написать агрегатор новостей.
Пользователь подает на вход адрес новостного сайта или его RSS-ленты и правило парсинга (формат правила — на усмотрение разработчика).
1. Интерфейс — веб. Остальное на выбор разработчика.
2. Правило — некий текст, из которого модуль должен понять, как парсить тот или иной сайт. Причем это может быть и не rss.

База данных агрегатора начинает автоматически пополняться новостями с этого сайта.<br>
У пользователя есть возможность просматривать список новостей из базы и искать их по подстроке в заголовке новости.<br>
В качестве примера требуется подключить два любых новостных сайта на выбор.<br>
Результат — исходный код агрегатора, а также рабочие адреса и правила парсинга, которые можно подать ему на вход.
Язык — Java. Хранилище — любая реляционная база данных. Плюсом будет использование ORM Hibernate.

## Правила парсинга:
type=auto/user<br>
content-type=xml/html<br>
feed-tag=... [optional]<br>
feed-class=...<br>
channel-tag=... [optional]<br>
channel-className=... [optional]<br>
item-class=...<br>
item-tag=... [optional]<br>
title-class=...<br>
title-tag=...[optional]<br>
description-class=...<br>
description-tag=...[optional]<br>
publishedDate-class=... [optional]<br>
publishedDate-tag=... [optional]<br>
link-class=... [optional]<br>
link-tag=... [optional]<br>

### Пример (https://news.yandex.ru/):
type=user<br>
content-type=html<br>
feed-tag=div<br>
feed-class=rubric<br>
channel-tag=a<br>
channel-className=title<br>
item-class=story__content<br>
item-tag=div<br>
title-class=story__title<br>
title-tag=a<br>
description-class=story__text<br>
description-tag=div<br>

### Пример (https://news.mail.ru/politics/):
type=user<br>
content-type=html<br>
feed-tag=div<br>
feed-class=cols<br>
channel-tag=a<br>
channel-className=title<br>
item-class=newsitem<br>
item-tag=div<br>
title-class=newsitem__title<br>
title-tag=a<br>
description-class=newsitem__text<br>
description-tag=div<br>

### Пример (https://lenta.ru/rss/):
type=user<br>
content-type=xml<br>
channel-tag=channel<br>
item-tag=item<br>
title-tag=title<br>
description-tag=description<br>
publishedDate-tag=pubDate<br>
link-tag=link<br>

### Другие сайты для тестирования (правило парсинга: type=auto):
https://snob.ru/rss/all <br>
http://www.pravda.com.ua/rss/