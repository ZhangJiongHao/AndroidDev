package com.bobomee.android.designpatterns.observer;

/**
 * Created on 16/8/20.下午11:41.
 *
 * @author bobomee.
 * @description:
 */
public class ConcreteObserver2 implements IObserver {
  @Override public void update(ISubject subject, Object o) {
    System.out.print("ConcreteObserver2 from -- >"+subject +"update -- >"+o);
  }
}
