// See LICENSE for license details.
package sifive.freedom.unleashed.u500nexys4ddrdevkit

import Chisel._

import freechips.rocketchip.config._
import freechips.rocketchip.subsystem._
import freechips.rocketchip.devices.debug._
import freechips.rocketchip.devices.tilelink._
import freechips.rocketchip.diplomacy._
import freechips.rocketchip.system._

import sifive.blocks.devices.gpio._
import sifive.blocks.devices.spi._
import sifive.blocks.devices.uart._

import sifive.fpgashells.devices.xilinx.xilinxnexys4ddrmig._

//-------------------------------------------------------------------------
// U500Nexys4DDRDevKitSystem
//-------------------------------------------------------------------------

class U500Nexys4DDRDevKitSystem(implicit p: Parameters) extends RocketSubsystem
    with HasPeripheryMaskROMSlave
    with HasPeripheryUART
    with HasPeripherySPI
    with HasPeripheryGPIO
    with HasMemoryXilinxNexys4DDRMIG {
  override lazy val module = new U500Nexys4DDRDevKitSystemModule(this)
}

class U500Nexys4DDRDevKitSystemModule[+L <: U500Nexys4DDRDevKitSystem](_outer: L)
  extends RocketSubsystemModuleImp(_outer)
    with HasRTCModuleImp
    with HasPeripheryUARTModuleImp
    with HasPeripherySPIModuleImp
    with HasPeripheryGPIOModuleImp
    with HasMemoryXilinxNexys4DDRMIGModuleImp {
  // Reset vector is set to the location of the mask rom
  val maskROMParams = p(PeripheryMaskROMKey)
  global_reset_vector := maskROMParams(0).address.U
}
