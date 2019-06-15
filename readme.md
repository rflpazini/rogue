# Rogue [![Build Status](https://travis-ci.com/rflpazini/rogue.svg?branch=master)](https://travis-ci.com/rflpazini/rogue) [![codecov](https://codecov.io/gh/rflpazini/rogue/branch/master/graph/badge.svg)](https://codecov.io/gh/rflpazini/rogue)

Transfer your money as Rogue does with powers 💸

![rogue-wallpaper](https://i.imgur.com/MftN7Ja.png)

## Introduction

This project was made with [Microprofile](https://microprofile.io/).

The generation of the executable jar file can be performed by issuing the following command

    $ ./mvnw clean package

This will create an executable jar file **rogue.jar** within the _target_ maven folder. It can be started by executing the following command

    java -jar target/rogue.jar

To launch the test page, open your browser at the following URL

    http://localhost:8181/rogue/index.html

### Insomnia REST client 

If you want to make your life easier, you should import Insomnia environment file that is in resources folder and you will be able to test all endpoints of Rogue.

![insomnia](https://i.imgur.com/aCcfFtq.png)


Here's a tutorial from [Insomnia](https://support.insomnia.rest/article/52-importing-and-exporting-data) website to help you when trying to do this.


## License

[MIT License](http://rflpazini.mit-license.org) :copyright: Rafael Pazini
