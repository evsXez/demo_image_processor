package evgeny.varov.demo.imageprocessor.MVP.Base

import org.greenrobot.eventbus.EventBus

interface IBase {
    val eventbus: EventBus
        get() = EventBus.getDefault()
}