package com.cout970.magneticraft.block

import coffee.cypher.mcextlib.extensions.aabb.to
import com.cout970.magneticraft.tileentity.TileTableSieve
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

/**
 * Created by cout970 on 13/06/2016.
 */
object BlockTableSieve : BlockBase(Material.WOOD, "table_sieve"), ITileEntityProvider {

    val TABLE_SIEVE_BOX = Vec3d.ZERO to Vec3d(1.0, 9.0 / 16.0, 1.0)

    override fun getBoundingBox(state: IBlockState?, source: IBlockAccess?, pos: BlockPos?) = TABLE_SIEVE_BOX

    override fun isFullBlock(state: IBlockState?) = false
    override fun isOpaqueCube(state: IBlockState?) = false
    override fun isFullCube(state: IBlockState?) = false
    override fun isVisuallyOpaque() = false

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, heldItem: ItemStack?, side: EnumFacing?, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if (heldItem != null) {
            val tile = world.getTileEntity(pos) as TileTableSieve
            if (tile.getRecipe(heldItem) != null) {
                val inserted = tile.inventory.insertItem(0, heldItem, false)
                player.setHeldItem(hand, inserted)
                return true
            }
        }
        return false
    }

    override fun createNewTileEntity(worldIn: World?, meta: Int): TileEntity? = TileTableSieve()
}