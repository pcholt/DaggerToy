import dagger.*
import javax.inject.Inject
import javax.inject.Scope

class MainRunner {
    fun run() {

    }
}

fun main() {
    MainRunner().apply {
        DaggerMainComponent.create().inject(this)
        run()
    }
}

@Scope
annotation class MainScope

@Component(modules = [MainModule::class])
@MainScope
interface MainComponent {
    fun inject(main: MainRunner)
}

@Module
abstract class MainModule {

    @Binds
    @MainScope
    abstract fun bindCalculator(calculator: CalculatorImpl) : Calculator

}

interface Calculator {
    fun add(a:Int,b:Int) : Int
}

@Module
@MainScope
abstract class AdditionOperatorModule {

    @Binds
    abstract fun bindAdditionOperator(additionOperator: AdditionOperator) : AdditionOperator

}

class CalculatorImpl @Inject constructor(private val op : AdditionOperator) : Calculator {
    override fun add(a: Int, b: Int): Int {
        return op.add(a,b) + 1
    }
}

class AdditionOperator {
    fun add(a:Int, b:Int) = a + b
}
