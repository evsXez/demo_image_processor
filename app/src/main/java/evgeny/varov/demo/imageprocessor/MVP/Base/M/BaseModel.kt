package evgeny.varov.demo.imageprocessor.MVP.Base.M

import evgeny.varov.demo.imageprocessor.MVP.Logic.Logic
import javax.inject.Inject
import evgeny.varov.demo.imageprocessor.MVP.Data.Data

/**
 * Created by evgeny on 14/03/2018.
 */
abstract class BaseModel(val logic: Logic, val data: Data) : IBaseModel
