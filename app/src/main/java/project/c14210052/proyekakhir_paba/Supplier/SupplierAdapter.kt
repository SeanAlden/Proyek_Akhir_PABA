package project.c14210052.proyekakhir_paba.Supplier

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import project.c14210052.proyekakhir_paba.R

class SupplierAdapter(
    private val suppliers: MutableList<Supplier>,
    private val onDeleteClick: (Int) -> Unit,
    private val onItemClick: (Supplier) -> Unit
) : RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder>(){

    lateinit var context : Context
    class SupplierViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaSupplier: TextView = itemView.findViewById(R.id.namaPrdk)
        val alamatSupplier: TextView = itemView.findViewById(R.id.kategoriPrdk)
        val kodePosSupplier: TextView = itemView.findViewById(R.id.kodeSupplier)
        val deleteBtn: ImageButton = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.recycler_supplier, parent, false)
        return SupplierViewHolder(view)
    }

    override fun getItemCount(): Int = suppliers.size

    override fun onBindViewHolder(holder: SupplierViewHolder, position: Int) {
        val supplier = suppliers[position]
        holder.namaSupplier.text = supplier.namaSupplier
        holder.alamatSupplier.text = supplier.alamatSupplier
        holder.kodePosSupplier.text = supplier.kodeSupplier

        holder.deleteBtn.setOnClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Hapus Supplier")
                setMessage("Apakah Anda yakin ingin menghapus supplier ini?")
                setPositiveButton("Ya") { dialog, _ ->
                    onDeleteClick(position)
                    dialog.dismiss()
                }
                setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                create()
                show()
            }
        }
        holder.itemView.setOnClickListener {
            onItemClick(supplier)
        }
    }
}