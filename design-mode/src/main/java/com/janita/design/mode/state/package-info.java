package com.janita.design.mode.state;

//状态模式
//1、定义、使用场景
//定义：在不同的状态下，对同一行为有不同的响应。状态模式把对象的行为包装在不同的状态中，每一个状态的对象都有一个相同的抽象状态基类，并实现基类对应的方法。这样当一个对象的状态发生改变时，其行为也会随之改变。
//
//使用场景：当一个对象的行为受其对应的状态的影响时。例如：手机的飞行模式打开和关闭是两个状态，在关闭飞行模式时可以打电话、发短信，打开飞行模式时，虽然打电话和发短信的功能存在，但却无法正常使用。
//
//2、角色
//State：抽象状态类或者接口，其中的方法表示对应状态下的行为。
//StateA、StateB：State的具体实现类，以实现对应状态下具体的行为。
//Context：维护当前对象所对应的状态。