API для работы с биржей ссылок SAPE.ru

Пока реализована только работа с обычными ссылками, контекста нет.

Содержит куски кода Copyright (c) 2007 Zsolt Szász <zsolt at lorecraft dot com>

Связаться со мной можно vasyl dot stashuk at gmail dot com или http://vasyas.blogspot.com/

Как использовать
1. Качаем со странички Downloads последнюю версию (сейчас она называется javasape-0.2-snapshot.jar) Кладем её в CLASSPATH проекта

2. Выкачиваем зависимости, если у вас их нет. Зависит Javasape от log4j и servlet-api, так что скорее всего у вас они уже есть. Скачать можно либо с доступных источников в Интернете, например, репозитория Maven, или зачекаутить исходники проекта и взять с них.

3. Для проверки работоспособности пишем маленькую программку:

  public static void main(String[] args) {
        BasicConfigurator.configure();
        
        String sapeUser = "sape-user-id";
        
        Sape sape = new Sape(sapeUser, "localhost", 1000, 10);
        
        sape.debug = true;
        
        SapePageLinks pageLinks = sape.getPageLinks("/", null);
        
        System.out.println(pageLinks.render());
    }

(вместо sape-user-id нужно подставить свой id в SAPE, конечно)

При запуске она должна выдать что-то вроде

   <sape_noindex><!-- Check code --></sape_noindex>
Это означает, что коннект с SAPE успешный

4. Чтобы встроить в страничку, нужно

создать один глобальный для приложения экземпляр класса Sape (он Thread-safe).
на каждый запрос вызывать один раз метод Sape.getPageLinks, передавая ему нужные параметры из HttpServletRequest?
в нужных местах в шаблонах странички добавить вызовы SapePageLinks?.render, передевая методу количество ссылок для отображения. Последний вызов должен быть без параметров, чтобы показать все оставшиеся ссылки.
В Velocity, например, это выглядит так: Создание объекта и подготовка контекста:

        Sape sape = new Sape("sape-user-id", "www.megasite.com", 1000, 5 * 60);
        ...
        context.put("sape", sape.getPageLinks(request.getRequestURI(), request.getCookies()));
И в шаблоне страницы

        <span class="footerLinks">
                $sape.render()
        </span>
Если нужны консультации по установке с использованем других технологий - всегда рад помочь.
