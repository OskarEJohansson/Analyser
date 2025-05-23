package logging

import org.slf4j.LoggerFactory
import java.util.logging.Logger


inline fun <reified T> logger(): Logger = LoggerFactory.getLogger(T::class.java) as Logger