package io.github.piotrkozuch.pm.action;

public interface Action<P, R> {

    R execute(P params);
}
